package tungp.android.bazarbooks.screens.vendor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.EmptyView
import tungp.android.bazarbooks.components.ErrorView
import tungp.android.bazarbooks.components.LoadingView
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorsScreen(
    navController: NavController,
    viewModel: VendorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.best_vendors_title_section),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* action */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when (uiState) {
            is BaseViewState.Data -> {
                val state = (uiState as BaseViewState.Data<VendorViewState>).value
                VendorsContent(
                    vendors = state.vendors,
                    categories = state.categories,
                    selectedCategory = state.selectedCategory,
                    onCategorySelected = { category ->
                        viewModel.onTriggerEvent(VendorViewEvent.SelectCategory(category))
                    },
                    navController = navController,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is BaseViewState.Error -> {
                ErrorView(
                    e = (uiState as BaseViewState.Error).throwable,
                    action = { viewModel.onTriggerEvent(VendorViewEvent.FetchVendors) }
                )
            }

            is BaseViewState.Loading -> {
                LoadingView(modifier = Modifier.padding(paddingValues))
            }

            else -> {
                EmptyView(modifier = Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun VendorsContent(
    vendors: List<Vendor>,
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {

        Text(
            "Our Vendors",
            color = Color(0xFFA5A5A5),
            style = BazarTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            "Vendords",
            color = Color(0xFF54408C),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category selector
        VendorCategorySelector(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = onCategorySelected
        )

        // Vendors list
        if (vendors.isEmpty()) {
            EmptyView(
                message = "No vendors available in this category",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(vendors) { vendor ->
                    VendorGridItem(
                        vendor = vendor,
                        onClick = {
                            // Handle navigation to vendor detail if needed
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun VendorGridItem(
    vendor: Vendor,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RectangleShape,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.7f),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Vendor image
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.foundation.Image(
                        painter = rememberAsyncImagePainter(model = vendor.cover),
                        contentDescription = vendor.title,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = vendor.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )

            RatingStars(
                value = vendor.rating,
                size = 12.dp,
                spaceBetween = 2.dp,
                style = RatingStarStyle.FILL
            )
        }
    }
}

@Composable
fun EmptyView(
    message: String = "No data available",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
fun PreviewVendorsScreen() {
    ThemedPreview {
        VendorsContent(
            vendors = PreviewData.vendors,
            categories = PreviewData.categories,
            selectedCategory = "All",
            onCategorySelected = { },
            navController = rememberNavController()
        )
    }
}

@Preview
@Composable
fun PreviewVendorGridItem() {
    ThemedPreview {
        VendorGridItem(
            vendor = PreviewData.vendors.first(),
            onClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewVendorDetailItem() {
    ThemedPreview {
        VendorDetailItem(
            vendor = PreviewData.vendors.first(),
            onClick = {}
        )
    }
}

@Composable
fun VendorDetailItem(
    vendor: Vendor,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Vendor image
            Card(
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                androidx.compose.foundation.Image(
                    painter = rememberAsyncImagePainter(model = vendor.cover),
                    contentDescription = vendor.title,
                    modifier = Modifier.size(80.dp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = vendor.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = vendor.authorName,
                    style = MaterialTheme.typography.bodyMedium
                )

                RatingStars(
                    value = vendor.rating,
                    style = RatingStarStyle.DEFAULT,
                    size = 16.dp
                )

                Text(
                    text = "${vendor.booksCount} books",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
} 