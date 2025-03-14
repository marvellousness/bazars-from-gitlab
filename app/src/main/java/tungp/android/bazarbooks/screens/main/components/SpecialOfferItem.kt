package tungp.android.bazarbooks.screens.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.constants.MyColors
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@Composable
fun SpecialOfferItem(
    offerBook: Book,
    onOfferItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF9F8FC),
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = 23.dp, vertical = 1.dp)
    ) {
        Column(modifier = Modifier.width(120.dp)) {
            Text(
                text = stringResource(R.string.special_offer),
                color = Color(0xFF121212),
                style = BazarTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                text = stringResource(R.string.discount_percent),
                color = Color(0xFF121212),
                fontSize = MyFontSize.body_medium,
                modifier = Modifier.padding(start = 2.dp, end = 2.dp, bottom = 16.dp)
            )
            OutlinedButton(
                onClick = { /* Handle button click */ },
                border = BorderStroke(0.dp, Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF54408C)
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(40.dp))
            ) {
                Text(
                    text = stringResource(R.string.order_now),
                    color = MyColors.surfaceContainerLowest,
                    fontSize = MyFontSize.body_medium,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
        CoilImage(
            imageModel = { offerBook.cover },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            modifier = Modifier
                .size(width = 99.dp, height = 145.dp)
                .clip(RoundedCornerShape(3.dp))
        )
    }
}

@Preview
@Composable
private fun SpecialOfferItemPreview() {
    SpecialOfferItem(PreviewData.topOfWeek.first(), onOfferItemClick = {})
}