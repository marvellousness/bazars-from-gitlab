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

    HomeContent(uiState)

    LaunchedEffect(key1 = Unit) {
        viewModel.onTriggerEvent(HomeEvent.LoadHomeFeeds)
    }
}

@Composable
fun HomeContent(uiState: BaseViewState<HomeState>) {
    when (uiState) {
        is BaseViewState.Data -> HomeContentDetail(uiState.value)
        BaseViewState.Empty -> EmptyView()
        is BaseViewState.Error -> ErrorView(
            e = uiState.throwable,
            action = {}
        )
        BaseViewState.Loading -> LoadingView()
    }
}

@Composable
fun HomeContentDetail(homeState: HomeState, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
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
            AuthorsContainer(homeState.authors)
        }
    }
}

@Composable
fun TopOfWeekContainer(
    books: List<Book>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
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
fun AuthorsContainer(authors: List<Author>) {
    SectionTitle(
        title = "Authors",
        onSeeAll = {
            // TODO: Navigate to the top of week list screen
        })
}

@Composable
fun BestVendorsContainer(bestVendors: List<Vendor>) {
    SectionTitle(
        title = "Best Vendors",
        onSeeAll = {
            // TODO: Navigate to the top of week list screen
        })
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
        itemContent = { book -> HorizontalBookItem(book = book, onBookItemClick = onShowBookDetail) },
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