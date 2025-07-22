package tungp.android.bazarbooks.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
internal fun IntroPagerIndicator(
    itemsList: List<IntroItem>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(itemsList.size) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) BazarTheme.colors.primary else BazarTheme.colors.secondary
            Box(
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp, bottom = 5.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }
    }
}
