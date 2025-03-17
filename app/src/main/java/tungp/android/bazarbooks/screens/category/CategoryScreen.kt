package tungp.android.bazarbooks.screens.category

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.Category
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.navigation.BookRouteScreen

@Composable
fun CategoryScreen(
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val selectedCategoryId by viewModel.selectedCategoryId.collectAsState()
    val books by viewModel.filteredBooks.collectAsState()

    val TAG = "~~~CategoryScreen"

    when (uiState) {
        is BaseViewState.Data -> {
            val data = (uiState as BaseViewState.Data<CategoryViewState>).value
            CategoryScreenContent(
                categories = data.categories,
                isLoading = data.isLoading,
                error = data.error,
                selectedCategoryId = data.selectedCategoryId,
                books = data.books,
                onCategorySelected = { categoryId ->
                    viewModel.onTriggerEvent(CategoryViewEvent.SelectCategory(categoryId))
                },
                onBookClicked = { book ->
                    Log.d(TAG, "CategoryScreen: 1")
                    // Navigate to book detail screen
                    book.bookId?.let { bookId ->
                        navController.navigate(BookRouteScreen.BookDetail.createRoute(bookId.toString()))
                    }
                }
            )
        }

        is BaseViewState.Loading -> {
            // Show loading - but we'll use our existing states for simplicity
            CategoryScreenContent(
                categories = categories,
                isLoading = true,
                error = null,
                selectedCategoryId = selectedCategoryId,
                books = books,
                onCategorySelected = { categoryId ->
                    viewModel.onTriggerEvent(CategoryViewEvent.SelectCategory(categoryId))
                },
                onBookClicked = { book ->
                    // Navigate to book detail screen
                    Log.d(TAG, "CategoryScreen: 2")

                    book.bookId?.let { bookId ->
                        navController.navigate(BookRouteScreen.BookDetail.createRoute(bookId.toString()))
                    }
                }
            )
        }

        is BaseViewState.Error -> {
            val exception = (uiState as BaseViewState.Error).throwable
            CategoryScreenContent(
                categories = categories,
                isLoading = false,
                error = exception.message ?: "Unknown error occurred",
                selectedCategoryId = selectedCategoryId,
                books = emptyList(),
                onCategorySelected = { categoryId ->
                    viewModel.onTriggerEvent(CategoryViewEvent.SelectCategory(categoryId))
                },
                onBookClicked = { book ->
                    Log.d(TAG, "CategoryScreen: 3")

                    // Navigate to book detail screen
                    book.bookId?.let { bookId ->
                        navController.navigate(BookRouteScreen.BookDetail.createRoute(bookId))
                    }
                }
            )
        }

        else -> {
            // Empty state or initial state
            CategoryScreenContent(
                categories = categories,
                isLoading = isLoading,
                error = error,
                selectedCategoryId = selectedCategoryId,
                books = books,
                onCategorySelected = { categoryId ->
                    viewModel.onTriggerEvent(CategoryViewEvent.SelectCategory(categoryId))
                },
                onBookClicked = { book ->
                    Log.d(TAG, "CategoryScreen: 4")

                    // Navigate to book detail screen
                    book.bookId?.let { bookId ->
                        navController.navigate(BookRouteScreen.BookDetail.createRoute(bookId))
                    }
                }
            )
        }
    }
}

@Composable
fun CategoryScreenContent(
    categories: List<Category>,
    isLoading: Boolean,
    error: String?,
    selectedCategoryId: String,
    books: List<Book>,
    onCategorySelected: (String) -> Unit,
    onBookClicked: (Book) -> Unit
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                // Horizontal category selector
                CategorySelector(
                    categories = categories,
                    selectedCategoryId = selectedCategoryId,
                    onCategorySelected = onCategorySelected
                )
            }
        }
    ) { paddingValues ->
        BookGrid(
            books = books,
            isLoading = isLoading,
            error = error,
            onBookClick = onBookClicked,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryScreenPreview() {
    val previewCategories = listOf(
        Category(id = "cat-001", name = "Fiction", bookCount = 120),
        Category(id = "cat-002", name = "Non-Fiction", bookCount = 85),
        Category(id = "cat-003", name = "Science Fiction", bookCount = 42)
    )

    CategoryScreenContent(
        categories = previewCategories,
        isLoading = false,
        error = null,
        selectedCategoryId = "cat-001",
        books = PreviewData.popular,
        onCategorySelected = {},
        onBookClicked = {}
    )
}