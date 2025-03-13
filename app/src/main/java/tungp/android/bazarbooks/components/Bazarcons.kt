package tungp.android.bazarbooks.components

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector
import tungp.android.bazarbooks.R

object NovaIcons

val NovaIcons.Add: NovaIcon.DrawableRes get() = NovaIcon.DrawableRes(R.drawable.ic_add)
val NovaIcons.Less: NovaIcon.DrawableRes get() = NovaIcon.DrawableRes(R.drawable.ic_less)

@Stable
sealed class NovaIcon {
    data class DrawableRes(@androidx.annotation.DrawableRes val drawableId: Int) : NovaIcon()
    data class AnimatedDrawableRes(@androidx.annotation.DrawableRes val drawableId: Int) :
        NovaIcon()

    data class VectorRes(val imageVector: ImageVector) : NovaIcon()

}