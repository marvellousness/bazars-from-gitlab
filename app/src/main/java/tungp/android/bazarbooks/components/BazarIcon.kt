package tungp.android.bazarbooks.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun BazarIcon(
    @DrawableRes iconResourceId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    Icon(
        painter = painterResource(id = iconResourceId),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}