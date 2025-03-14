package tungp.android.bazarbooks.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.domain.model.Vendor


@Composable
fun VendorItem(
    vendor: Vendor,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 7.dp, end = 7.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .width(80.dp)
                .height(80.dp)
                .background(
                    color = Color(0xFFF9F9F9),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            CoilImage(
                imageModel = { vendor.cover },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun PreviewVendorItem() {
    VendorItem(PreviewData.vendors.first())
}