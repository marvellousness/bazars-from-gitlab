package tungp.android.bazarbooks.components.button

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ButtonSize {
    /**
     * Medium button is the default button size (recommended).
     */
    Medium {
        override val height = 44.dp
        override val contentVerticalPadding = 12.dp
    },

    Large {
        override val height = 56.dp
        override val contentVerticalPadding = 18.dp
    };

    internal abstract val height: Dp
    internal abstract val contentVerticalPadding: Dp
}