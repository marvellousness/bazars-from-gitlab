package tungp.android.bazarbooks.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.data.remote.network.model.ApiData.topOfWeek
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.ui.theme.BazarPreviewTheme
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@Composable
fun HorizontalBookItem(
    book: Book,
    onBookItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onBookItemClick(book.isbn) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            imageModel = { book.cover },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .padding(end = 12.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .width(127.dp)
                .height(150.dp)
        )

        Text(
            text = book.title,
            style = BazarTheme.typography.bodyMedium
        )
        Text(
            "$ ${book.price}",
            color = BazarTheme.colors.onSurface,
            fontSize = MyFontSize.body_small,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HorizontalBookItemPreview() {
    BazarPreviewTheme {
        val book = topOfWeek.first()
        HorizontalBookItem(book = book, onBookItemClick = {})
    }
}