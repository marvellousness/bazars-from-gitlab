package tungp.android.bazarbooks.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tungp.android.bazarbooks.components.EmptyView
import tungp.android.bazarbooks.components.ErrorView
import tungp.android.bazarbooks.components.LoadingView
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.onTriggerEvent(HomeEvent.LoadHomeFeeds)
    }
    HomeContent(uiState)
}

@Composable
fun HomeContent(uiState: BaseViewState<HomeState>) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is BaseViewState.Data -> HomeContentContainer(uiState.value)
            BaseViewState.Empty -> EmptyView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.throwable,
                action = {}
            )

            BaseViewState.Loading -> LoadingView()
        }
    }
}

@Composable
fun HomeContentContainer(homeState: HomeState, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(BazarTheme.spacing.extraMedium),
        contentPadding = PaddingValues(vertical = BazarTheme.spacing.extraMedium)
    ) {

        item {
            TopOfWeekContainer(homeState.topOfWeeks)
        }

        item {
            BestVendorsContainer(homeState.bestVendors)
        }

        item {
            TopAuthorsContainer(homeState.authors)
        }
    }
}

@Composable
fun TopOfWeekContainer(
    books: List<Book>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SectionTitle(
            title = "Top of Week",
            onSeeAll = {
                // TODO: Navigate to the top of week list screen
            })
        BooksContainer(books = books, onShowBookDetail = {
            // TODO: Navigate to the book details screen
        })
    }
}

@Composable
fun TopAuthorsContainer(authors: List<Author>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SectionTitle(
            title = "Authors",
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
    modifier: Modifier = Modifier
) {
    ContainerContent(
        modifier = modifier,
        items = authors,
        itemContent = { book ->
            HorizontalAuthorItem(
                author = book,
                onAuthorItemClick = onAuthorItemClick
            )
        },
        placeholderContent = { HorizontalItemPlaceholder() }
    )
}

@Composable
fun BestVendorsContainer(bestVendors: List<Vendor>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SectionTitle(
            title = "Best Vendors",
            onSeeAll = {
                // TODO: Navigate to the top of week list screen
            })
    }
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
fun HorizontalItemPlaceholder() {
    Text("TODO NEXT")
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
        horizontalArrangement = Arrangement.spacedBy(BazarTheme.spacing.medium),
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