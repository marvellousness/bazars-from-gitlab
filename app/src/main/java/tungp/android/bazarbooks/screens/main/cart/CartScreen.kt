package tungp.android.bazarbooks.screens.main.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tungp.android.bazarbooks.components.EmptyView
import tungp.android.bazarbooks.components.ErrorView
import tungp.android.bazarbooks.components.LoadingView
import tungp.android.bazarbooks.domain.model.CartItem
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.navigation.CartRouteScreen
import tungp.android.bazarbooks.ui.theme.ThemedPreview

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is BaseViewState.Loading -> LoadingView()
            is BaseViewState.Error -> ErrorView(
                e = (uiState as BaseViewState.Error).throwable,
                action = { viewModel.onTriggerEvent(CartEvent.LoadCart) }
            )

            is BaseViewState.Data -> {
                val data = (uiState as BaseViewState.Data<CartState>).value
                CartContent(
                    cartItems = data.cartItems,
                    totalPrice = data.totalPrice,
                    onUpdateQuantity = { itemId, quantity ->
                        viewModel.onTriggerEvent(CartEvent.UpdateQuantity(itemId, quantity))
                    },
                    onRemoveItem = { itemId ->
                        viewModel.onTriggerEvent(CartEvent.RemoveFromCart(itemId))
                    },
                    onCheckout = {
                        navController.navigate(CartRouteScreen.CartDetail.route)
                    }
                )
            }

            BaseViewState.Empty -> EmptyView()
        }
    }
}

@Composable
private fun CartContent(
    cartItems: List<CartItem>,
    totalPrice: Double,
    onUpdateQuantity: (String, Int) -> Unit,
    onRemoveItem: (String) -> Unit,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Shopping Cart",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (cartItems.isEmpty()) {
            EmptyCartContent()
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cartItems, key = { it.id }) { item ->
                    CartItemCard(
                        cartItem = item,
                        onUpdateQuantity = { quantity -> onUpdateQuantity(item.id, quantity) },
                        onRemove = { onRemoveItem(item.id) }
                    )
                }
            }

            CartSummary(
                totalPrice = totalPrice,
                onCheckout = onCheckout
            )
        }
    }
}

@Composable
private fun EmptyCartContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Your cart is empty",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CartItemCard(
    cartItem: CartItem,
    onUpdateQuantity: (Int) -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cartItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = cartItem.author,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "$${cartItem.price}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QuantitySelector(
                    quantity = cartItem.quantity,
                    onQuantityChange = onUpdateQuantity
                )
                TextButton(onClick = onRemove) {
                    Text("Remove")
                }
            }
        }
    }
}

@Composable
private fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            onClick = { if (quantity > 1) onQuantityChange(quantity - 1) }
        ) {
            Text("-")
        }
        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
        IconButton(
            onClick = { if (quantity < 10) onQuantityChange(quantity + 1) }
        ) {
            Text("+")
        }
    }
}

@Composable
private fun CartSummary(
    totalPrice: Double,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total:",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${String.format("%.2f", totalPrice)}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCheckout,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Proceed to Checkout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    val items = listOf(
        CartItem(
            id = "cart_1",
            bookId = "4",
            title = "The Alchemist",
            cover = "http://covers.openlibrary.org/b/id/13151170-L.jpg",
            author = "Paulo Coelho",
            quantity = 1,
            price = 14.99,
            totalPrice = 14.99
        )
    )
    ThemedPreview {
        CartContent(
            cartItems = items,
            totalPrice = 100.0,
            onCheckout = {},
            onUpdateQuantity = { _, _ -> },
            onRemoveItem = {})
    }
}