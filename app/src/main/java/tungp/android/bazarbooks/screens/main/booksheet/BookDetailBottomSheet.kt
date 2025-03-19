package tungp.android.bazarbooks.screens.main.booksheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
    onContinueShopping: () -> Unit,
    onAddToCart: (bookId: String, amount: Int) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        BookDetailSheetContent(
            book = book,
            onContinueShopping = onContinueShopping,
            onAddToCart = onAddToCart
        )
    }
}

@Composable
private fun BookDetailSheetContent(
    book: Book,
    onContinueShopping: () -> Unit,
    onAddToCart: (bookId: String, amount: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val quantity = remember { mutableIntStateOf(1) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        BookCoverImage(book.cover)
        Spacer(modifier = Modifier.height(16.dp))
        BookHeader(book.title)
        Spacer(modifier = Modifier.height(8.dp))
        BookAuthor(book.author)
        Spacer(modifier = Modifier.height(8.dp))
        BookDescription(book.description ?: "")
        Spacer(modifier = Modifier.height(8.dp))
        BookRating(book.rating)
        Spacer(modifier = Modifier.height(8.dp))
        PriceAndQuantitySection(
            price = book.price ?: 0.00,
            initialQuantity = 1,
            onQuantityChanged = { newQuantity -> quantity.intValue = newQuantity }
        )
        Spacer(modifier = Modifier.height(24.dp))
        ActionButtons(
            onContinueShopping = onContinueShopping,
            onViewCart = { book.bookId?.let { onAddToCart(it, quantity.intValue) } }
        )
    }
}

@Composable
private fun BookCoverImage(coverUrl: String?) {
    AsyncImage(
        model = coverUrl,
        contentDescription = "Book cover",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun BookHeader(title: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_love_fill),
            contentDescription = "Favorite",
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
        )
    }
}

@Composable
private fun BookAuthor(author: String) {
    Text(
        text = "By $author",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun BookDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun BookRating(rating: Int) {
    Text(
        text = "Review",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold
    )
    Rating(rating = rating, maxRating = 5) {}
}

@Composable
private fun PriceAndQuantitySection(
    price: Double,
    initialQuantity: Int = 0,
    onQuantityChanged: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        QuantityPicker(
            initialAmount = initialQuantity,
            maxAmount = 10,
            modifier = Modifier.width(100.dp),
            onAmountChanged = onQuantityChanged
        )
        Text(
            text = "$$price",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ActionButtons(
    onContinueShopping: () -> Unit,
    onViewCart: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PrimaryButton(
            text = "Continue Shopping",
            onClick = onContinueShopping,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        SecondaryButton(
            text = "View Cart",
            onClick = onViewCart,
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BookDetailBottomSheetPreview() {
    ThemedPreview {
        BookDetailSheetContent(
            book = PreviewData.topOfWeek.first(),
            onContinueShopping = {},
            onAddToCart = { _, _ -> }
        )
    }
}