package tungp.android.bazarbooks.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.PreviewData

@Composable
fun BookGrid(
    books: List<Book>,
    isLoading: Boolean,
    error: String?,
    onBookClick: (Book) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            isLoading && books.isEmpty() -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            error != null && books.isEmpty() -> {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }

            books.isEmpty() -> {
                Text(
                    text = "No books found for this category",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(books) { book ->
                        BookCard(
                            book = book,
                            onClick = { onBookClick(book) }
                        )
                    }
                }
            }
        }

        // Show a loading indicator when refreshing with existing data
        if (isLoading && books.isNotEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookGridPreview() {
    BookGrid(
        books = PreviewData.popular,
        isLoading = false,
        error = null,
        onBookClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun EmptyBookGridPreview() {
    BookGrid(
        books = emptyList(),
        isLoading = false,
        error = null,
        onBookClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingBookGridPreview() {
    BookGrid(
        books = emptyList(),
        isLoading = true,
        error = null,
        onBookClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorBookGridPreview() {
    BookGrid(
        books = emptyList(),
        isLoading = false,
        error = "Failed to load books",
        onBookClick = {}
    )
} 