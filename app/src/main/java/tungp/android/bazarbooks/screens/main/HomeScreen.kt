package tungp.android.bazarbooks.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.EmptyView
import tungp.android.bazarbooks.components.ErrorView
import tungp.android.bazarbooks.components.LoadingView
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.navigation.BookRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.screens.main.booksheet.BookDetailBottomSheet
import tungp.android.bazarbooks.screens.main.components.AuthorItem
import tungp.android.bazarbooks.screens.main.components.HorizontalBookItem
import tungp.android.bazarbooks.screens.main.components.HorizontalItemPlaceholder
import tungp.android.bazarbooks.screens.main.components.SectionTitle
import tungp.android.bazarbooks.screens.main.components.SpecialOffersCarousel
import tungp.android.bazarbooks.screens.main.components.VendorItem
import tungp.android.bazarbooks.screens.main.components.VendorItemPlaceholder
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.onTriggerEvent(HomeEvent.LoadHomeFeeds)
    }
    HomeContent(uiState, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    uiState: BaseViewState<HomeState>,
    navController: NavController,
) {
    // State for the selected book and bottom sheet visibility
    var selectedBook by remember { mutableStateOf<Book?>(null) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Column(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is BaseViewState.Data -> HomeContentContainer(
                uiState.value,
                navController,
                onBookSelected = { book ->
                    selectedBook = book
                }
            )

            BaseViewState.Empty -> EmptyView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.throwable,
                action = {}
            )

            BaseViewState.Loading -> LoadingView()
        }
    }

    // Show bottom sheet if a book is selected
    selectedBook?.let { book ->
        BookDetailBottomSheet(
            book = book,
            onDismiss = { selectedBook = null },
            onContinueShopping = { selectedBook = null },
            onAddToCart = { bookId, amount ->
                // Handle adding to cart functionality here
                // For example, call a ViewModel method to add to cart
                // viewModel.addToCart(bookId, amount)
                selectedBook = null
            },
            sheetState = sheetState
        )
    }
}

@Composable
fun HomeContentContainer(
    homeState: HomeState,
    navController: NavController,
    onBookSelected: (Book) -> Unit,
) {
    LazyColumn {
        item {
            SpecialOffersContainer(
                offers = homeState.specialOffers,
                navController = navController
            )
        }
        item {
            TopOfWeekContainer(
                books = homeState.topOfWeeks,
                navController = navController,
                onBookSelected = onBookSelected
            )
        }

        item {
            BestVendorsContainer(
                bestVendors = homeState.bestVendors,
                navController = navController
            )
        }

        item {
            TopAuthorsContainer(homeState.authors)
        }
    }
}

@Composable
fun SpecialOffersContainer(
    offers: List<Book>,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(modifier = modifier) {
        OffersContainer(specialOffers = offers, onOfferItemClick = { bookId ->
            navController.navigate(BookRouteScreen.BookDetail.createRoute(bookId))
        })
    }
}

@Composable
fun TopOfWeekContainer(
    books: List<Book>,
    modifier: Modifier = Modifier,
    navController: NavController,
    onBookSelected: (Book) -> Unit,
) {
    Column(modifier = modifier) {
        SectionTitle(
            title = stringResource(R.string.top_of_week_title_section),
            onSeeAll = {
                // TODO: Navigate to the top of week list screen
            })
        BooksContainer(books = books, onShowBookDetail = { bookId ->
//            navController.navigate(BookRouteScreen.BookDetail.createRoute(bookId))
//             Find the book with the given ID and show details
            val book = books.find { it.bookId == bookId }
            book?.let {
                onBookSelected(it)
            }
        })
    }
}

@Composable
fun TopAuthorsContainer(
    authors: List<Author>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SectionTitle(
            title = stringResource(R.string.authors_tittle_section),
            onSeeAll = {
                // TODO: Navigate to the top of week list screen
            })
        AuthorsContainer(
            authors = authors,
            onAuthorItemClick = { author ->
                // TODO: Navigate to the author details screen
            },
            modifier = modifier
        )
    }
}

@Composable
fun AuthorsContainer(
    authors: List<Author>,
    onAuthorItemClick: (Author) -> Unit,
    modifier: Modifier = Modifier,
) {
    ContainerContent(
        modifier = modifier,
        items = authors,
        itemContent = { book ->
            AuthorItem(
                author = book,
                onAuthorItemClick = onAuthorItemClick
            )
        },
        placeholderContent = { HorizontalItemPlaceholder() }
    )
}

@Composable
fun BestVendorsContainer(
    bestVendors: List<Vendor>,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(modifier = modifier) {
        SectionTitle(
            title = stringResource(R.string.best_vendors_title_section),
            onSeeAll = {
                navController.navigate(Graph.VendorGraph)
            })
        ContainerContent(
            modifier = modifier,
            items = bestVendors,
            itemContent = { vendor ->
                VendorItem(vendor = vendor)
            }, placeholderContent = { VendorItemPlaceholder() }
        )
    }
}

@Composable
fun OffersContainer(
    specialOffers: List<Book>,
    onOfferItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SpecialOffersCarousel(
        specialOffers = specialOffers,
        onOfferItemClick = onOfferItemClick,
        modifier = modifier
    )
}

@Composable
fun BooksContainer(
    books: List<Book>,
    onShowBookDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    ContainerContent(
        modifier = modifier,
        items = books,
        itemContent = { book ->
            HorizontalBookItem(
                book = book,
                onBookItemClick = onShowBookDetail
            )
        },
        placeholderContent = { HorizontalItemPlaceholder() }
    )
}

@Composable
private fun <T> ContainerContent(
    modifier: Modifier,
    items: List<T>,
    itemContent: @Composable LazyItemScope.(T) -> Unit,
    placeholderContent: @Composable LazyItemScope.(Int) -> Unit,
    shouldShowPlaceholder: Boolean = items.isEmpty(),
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = BazarTheme.spacing.medium)
    ) {
        if (shouldShowPlaceholder) {
            items(count = PlaceholderCount, itemContent = placeholderContent)
        } else {
            items(items = items, itemContent = itemContent)
        }
    }
}

private const val PlaceholderCount = 20