package tungp.android.bazarbooks.screens.author

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.BackNavigationAction
import tungp.android.bazarbooks.components.BazarAppBar
import tungp.android.bazarbooks.components.RatingDisplay
import tungp.android.bazarbooks.components.RatingStarStyle
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.PreviewData.favoriteBooksByUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorDetailScreen(
    navController: NavController,
    author: Author,
) {
    Scaffold(
        topBar = {
            BazarAppBar(
                title = {
                    Text(
                        text = "Author Detail",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = { BackNavigationAction(onClick = { navController.navigateUp() }) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            AuthorProfileScreen(author = author)
        }
    }
}


@Composable
fun AuthorProfileScreen(author: Author) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AuthorHeader(author = author)
        Spacer(modifier = Modifier.height(24.dp))
        AboutSection(aboutText = author.title)
        Spacer(modifier = Modifier.height(24.dp))
        ProductsSection(books = favoriteBooksByUser)
    }
}


@Composable
fun AuthorHeader(author: Author) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CoilImage(
            imageModel = { author.cover },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .padding(end = 11.dp)
                .clip(shape = CircleShape)
                .size(96.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = author.authorName,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = author.authorName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        RatingDisplay(
            value = author.rating,
            style = RatingStarStyle.DEFAULT,
            size = 26.dp
        )
    }
}

@Composable
fun AboutSection(aboutText: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "About",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = aboutText,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = Color.DarkGray
        )
    }
}

@Composable
fun ProductsSection(books: List<Book>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Products",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(books) { book ->
                BookItem(book = book)
            }
        }
    }
}


@Composable
fun BookItem(book: Book) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.width(IntrinsicSize.Max) // Ensures title and price wrap correctly
    ) {
        CoilImage(
            imageModel = { book.cover },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .padding(end = 11.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .width(100.dp)
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 2, // Allow title to wrap
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = book.price.toString(),
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis
        )
    }
}