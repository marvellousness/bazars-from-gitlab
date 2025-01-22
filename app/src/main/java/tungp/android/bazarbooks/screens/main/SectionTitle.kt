package tungp.android.bazarbooks.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tungp.android.bazarbooks.ui.theme.constants.MyFontSize

@Composable
fun SectionTitle(
    title: String,
    onSeeAll: (Int) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 17.dp, start = 23.dp, end = 23.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            color = Color(0xFF121212),
            fontSize = 18.sp,
        )
        Text(
            "See all",
            color = Color(0xFF54408C),
            fontSize = MyFontSize.body_medium,
            modifier = Modifier.clickable {
                onSeeAll(1)
            }
        )
    }
}