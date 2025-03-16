package tungp.android.bazarbooks.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.ThemedPreview

@Composable
fun AuthorItem(
    author: Author,
    onAuthorItemClick: (Author) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onAuthorItemClick(author) },
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = author.cover,
            contentDescription = author.authorName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 12.dp)
                .clip(CircleShape)
                .size(102.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = author.authorName,
            style = BazarTheme.typography.bodySmall,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            author.title,
            color = Color(0xFFA5A5A5),
            style = BazarTheme.typography.bodySmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HorizontalBookItemPreview() {
    ThemedPreview {
        AuthorItem(author = PreviewData.authors.first(), onAuthorItemClick = {})
    }
}