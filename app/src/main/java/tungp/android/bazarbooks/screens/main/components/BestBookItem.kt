package tungp.android.bazarbooks.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.PreviewData.topOfWeek
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.PreviewTheme

@Composable
fun HorizontalBookItem(
    book: Book,
    onBookItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .clickable { onBookItemClick(book.isbn) },
        verticalArrangement = Arrangement.Center
    ) {
        CoilImage(
            imageModel = { book.cover },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier

                .clip(shape = RoundedCornerShape(8.dp))
                .width(127.dp)
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = book.title,
            style = BazarTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "$ ${book.price}",
            color = BazarTheme.colors.primary,
            style = BazarTheme.typography.bodySmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHorizontalBookItem() {
    PreviewTheme {
        HorizontalBookItem(book = topOfWeek.first(), onBookItemClick = {})
    }
}