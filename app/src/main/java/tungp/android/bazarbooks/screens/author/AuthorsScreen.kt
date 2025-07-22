package tungp.android.bazarbooks.screens.author

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.EmptyView
import tungp.android.bazarbooks.components.ErrorView
import tungp.android.bazarbooks.components.LoadingView
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.ThemedPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorsScreen(
    navController: NavController,
    viewModel: AuthorViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.best_authors_title_section),
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
                val state = (uiState as BaseViewState.Data<AuthorViewState>).value
                AuthorsContent(
                    authors = state.authors,
                    categories = state.categories,
                    selectedCategory = state.selectedCategory,
                    onCategorySelected = { category ->
                        viewModel.onTriggerEvent(AuthorViewEvent.SelectCategory(category))
                    },
                    navController = navController,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is BaseViewState.Error -> {
                ErrorView(
                    e = (uiState as BaseViewState.Error).throwable,
                    action = { viewModel.onTriggerEvent(AuthorViewEvent.FetchAuthors) }
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
fun AuthorsContent(
    authors: List<Author>,
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            "Our Authors",
            color = Color(0xFFA5A5A5),
            style = BazarTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            "Authors",
            color = Color(0xFF54408C),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category selector
        AuthorCategorySelector(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = onCategorySelected
        )

        // Authors list
        if (authors.isEmpty()) {
            EmptyView(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(authors) { author ->
                    AuthorItemView(
                        author = author,
                        onClick = {
                            val json = kotlinx.serialization.json.Json.encodeToString(author)
                            val encodedJson = java.net.URLEncoder.encode(
                                json,
                                java.nio.charset.StandardCharsets.UTF_8.toString()
                            )
                            navController.navigate("authorDetail/$encodedJson")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AuthorItemView(
    author: Author,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(bottom = 35.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        CoilImage(
            imageModel = { author.cover },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .padding(end = 11.dp)
                .clip(shape = RoundedCornerShape(100.dp))
                .width(68.dp)
                .height(68.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                author.authorName,
                color = Color(0xFF111111),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 13.dp)
            )
            Text(
                text = author.title,
                color = Color(0xFF66707A),
                style = BazarTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
fun AuthorCategorySelector(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            FilterChip(
                selected = category == selectedCategory,
                onClick = { onCategorySelected(category) },
                label = { Text(category) }
            )
        }
    }
}

@Preview
@Composable
fun AuthorsScreenPreview() {
    ThemedPreview {
        AuthorsContent(
            authors = PreviewData.authors,
            categories = PreviewData.categories,
            selectedCategory = PreviewData.categories.first(),
            onCategorySelected = {},
            navController = rememberNavController(),
        )
    }
} 