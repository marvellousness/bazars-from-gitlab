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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.LoadingView
import tungp.android.bazarbooks.components.QuantityPicker
import tungp.android.bazarbooks.components.Rating
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.components.button.SecondaryButton
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.ui.theme.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailBottomSheet(
    book: Book,
    onDismiss: () -> Unit,
    onContinueShopping: () -> Unit,
    onAddToCart: (bookId: String, amount: Int) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    viewModel: BookDetailBottomSheetViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // Load book detail when the sheet is shown - with a safe null check
    LaunchedEffect(key1 = Unit) {
        book.bookId?.let { bookId ->
            viewModel.onTriggerEvent(BookDetailBottomSheetEvent.LoadBookDetail(bookId))
        }
    }
    
    // Handle add to cart result
    LaunchedEffect(key1 = Unit) {
        viewModel.addToCartResult.collect { result ->
            if (result != null) {
                // Cart addition was successful, dismiss the sheet in a coroutine
                coroutineScope.launch {
                    try {
                        sheetState.hide()
                    } finally {
                        onDismiss()
                    }
                }
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = {
            // Use a single callback for dismissal to avoid multiple calls
            onDismiss()
        },
        sheetState = sheetState
    ) {
        when (uiState) {
            is BaseViewState.Loading -> {
                LoadingView()
            }
            is BaseViewState.Data -> {
                val state = (uiState as BaseViewState.Data<BookDetailBottomSheetState>).value
                BookDetailSheetContent(
                    book = state.book ?: book,
                    quantity = state.quantity,
                    isAddingToCart = state.isAddingToCart,
                    onQuantityChanged = { newQuantity ->
                        viewModel.onTriggerEvent(BookDetailBottomSheetEvent.UpdateQuantity(newQuantity))
                    },
                    onContinueShopping = {
                        coroutineScope.launch {
                            try {
                                sheetState.hide()
                            } finally {
                                onContinueShopping()
                            }
                        }
                    },
                    onAddToCart = { bookId, quantity ->
                        viewModel.onTriggerEvent(BookDetailBottomSheetEvent.AddToCart(bookId, quantity))
                    }
                )
            }
            else -> {
                // Show fallback content with the book passed to the composable
                BookDetailSheetContentFallback(
                    book = book,
                    onContinueShopping = {
                        coroutineScope.launch {
                            try {
                                sheetState.hide()
                            } finally {
                                onContinueShopping()
                            }
                        }
                    },
                    onAddToCart = { bookId, quantity ->
                        coroutineScope.launch {
                            try {
                                sheetState.hide()
                            } finally {
                                onAddToCart(bookId, quantity)
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BookDetailSheetContent(
    book: Book,
    quantity: Int,
    isAddingToCart: Boolean,
    onQuantityChanged: (Int) -> Unit,
    onContinueShopping: () -> Unit,
    onAddToCart: (bookId: String, amount: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
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
            quantity = quantity,
            onQuantityChanged = onQuantityChanged,
            enabled = !isAddingToCart
        )
        Spacer(modifier = Modifier.height(24.dp))
        ActionButtons(
            onContinueShopping = onContinueShopping,
            onViewCart = { book.bookId?.let { onAddToCart(it, quantity) } },
            isLoading = isAddingToCart
        )
    }
}

@Composable
private fun BookDetailSheetContentFallback(
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
            quantity = quantity.intValue,
            onQuantityChanged = { newQuantity -> quantity.intValue = newQuantity }
        )
        Spacer(modifier = Modifier.height(24.dp))
        ActionButtons(
            onContinueShopping = onContinueShopping,
            onViewCart = { book.bookId?.let { onAddToCart(it, quantity.intValue) } },
            isLoading = false
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
    quantity: Int = 1,
    onQuantityChanged: (Int) -> Unit,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        QuantityPicker(
            initialAmount = quantity,
            maxAmount = 10,
            modifier = Modifier.width(100.dp),
            onAmountChanged = onQuantityChanged,
            //enabled = enabled
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
    isLoading: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PrimaryButton(
            text = "Continue Shopping",
            onClick = onContinueShopping,
            modifier = Modifier.weight(1f),
            //enabled = !isLoading
        )
        Spacer(modifier = Modifier.width(16.dp))
        SecondaryButton(
            text = if (isLoading) "Adding..." else "Add to Cart",
            onClick = onViewCart,
            modifier = Modifier.weight(1f),
            //enabled = !isLoading
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BookDetailBottomSheetPreview() {
    ThemedPreview {
        BookDetailSheetContentFallback(
            book = PreviewData.topOfWeek.first(),
            onContinueShopping = {},
            onAddToCart = { _, _ -> }
        )
    }
}