package tungp.android.bazarbooks.screens.main.booksheet

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.QuantityPicker
import tungp.android.bazarbooks.components.Rating
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.components.button.SecondaryButton
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.ui.theme.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailBottomSheet(
    book: Book,
    onDismiss: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        BookDetailSheetContent(
            book = book,
            onAddToCart = { bookId, amount ->
                // Handle add to cart
                coroutineScope.launch {
                    sheetState.hide()
                    onDismiss()
                }

                Log.d("~~~TAG", "BookDetailBottomSheet: amount=$amount")
            }
        )
    }
}


@Composable
private fun BookDetailSheetContent(
    book: Book,
    onAddToCart: (bookId: String, amount: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var amount = remember { mutableIntStateOf(1) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Book Cover Image
        AsyncImage(
            model = book.cover,
            contentDescription = "Book cover",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_love_fill),
                contentDescription = "Love icon",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "By ${book.author}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.description ?: "",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Review",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Rating(rating = book.rating, maxRating = 5) {}
        Spacer(modifier = Modifier.height(8.dp))

        // Price and Rating Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            QuantityPicker(
                initialAmount = 1, maxAmount = 10, modifier = Modifier.width(100.dp),
                onAmountChanged = { amountChanged ->
                    amount.intValue = amountChanged
                })
            Text(
                text = "$${book.price}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PrimaryButton(
                text = "Continue Shopping",
                onClick = { /* TODO: Add to cart */ },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))

            SecondaryButton(
                text = "View Cart",
                onClick = {
                    book.bookId?.let {
                        onAddToCart(book.bookId, amount.intValue)
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BookDetailBottomSheetPreview() {
    ThemedPreview {
        BookDetailSheetContent(book = PreviewData.topOfWeek.first(), onAddToCart = { _, _ -> })
    }
}