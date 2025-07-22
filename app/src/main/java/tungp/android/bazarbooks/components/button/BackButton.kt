package tungp.android.bazarbooks.components.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.BazarIcon
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
fun BazarBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = BazarTheme.colors.onBackground
) {
    BazarIconButton(
        modifier = modifier
            .size(BazarBackButtonShapeSize)
            .background(
                color = BazarTheme.colors.surface, shape = BazarTheme.shapes.small
            ),
        iconResourceId = R.drawable.ic_arrow_back,
        contentDescription = stringResource(id = R.string.back),
        onClick = onClick,
        tint = tint
    )
}

@Composable
fun BazarIconButton(
    @DrawableRes iconResourceId: Int,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    BazarIconButtonContent(modifier = modifier, onClick = onClick) {
        BazarIcon(
            iconResourceId = iconResourceId, contentDescription = contentDescription, tint = tint
        )

    }
}

@Composable
private fun BazarIconButtonContent(
    onClick: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentSize provides 10.dp
    ) {
        IconButton(modifier = modifier, onClick = onClick, content = content)
    }
}

private val BazarBackButtonShapeSize = 32.dp
