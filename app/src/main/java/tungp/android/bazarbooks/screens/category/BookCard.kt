package tungp.android.bazarbooks.screens.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.ui.theme.ThemedPreview
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@Composable
fun BookCard(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        CoilImage(
            imageModel = { book.cover },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .width(158.dp)
                .height(187.dp)
        )

        Text(
            text = book.title,
            color = Color(0xFF121212),
            fontSize = MyFontSize.body_large,
        )
        
        book.rating?.let { rating ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = if (index < rating) Icons.Filled.Star else Icons.Filled.StarBorder,
                        contentDescription = "Rating star",
                        tint = if (index < rating) Color(0xFFFFC107) else Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
        
        Text(
            "$${book.price}",
            color = Color(0xFF54408C),
            fontSize = MyFontSize.body_medium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BookCardPreview() {
    ThemedPreview {
        BookCard(
            book = PreviewData.popular.first(),
            onClick = { /* todo: add click handler */ },
            Modifier
        )
    }
}