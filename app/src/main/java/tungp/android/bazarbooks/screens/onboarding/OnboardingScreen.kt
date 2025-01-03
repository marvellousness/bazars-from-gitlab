package tungp.android.bazarbooks.screens.onboarding

import android.util.Log
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.BazarSurface
import tungp.android.bazarbooks.components.button.ButtonShape
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.components.button.SecondaryButton
import tungp.android.bazarbooks.components.button.TextButton
import tungp.android.bazarbooks.ui.theme.BazarTheme


private val TAG = "~~~Onboarding"

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    gotoAuth: () -> Unit,
) {
    val introItems = viewModel.state.introItemList
    val pagerState: PagerState = rememberPagerState(pageCount = { introItems.size })

    val state by viewModel.stateX.collectAsState()
    Log.d(TAG, "OnboardingScreen: ${Gson().toJson(state)}")

    BazarSurface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextButton(
                    modifier = Modifier.padding(top = 15.dp, start = 15.dp),
                    text = stringResource(R.string.skip),
                    onClick = {
                        gotoAuth()
                    },
                )
            }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .weight(1f)
            ) {
                IntroView(itemsList = introItems, pagerState)
            }
            PrimaryButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp),
                shape = ButtonShape.Pill,
                text = stringResource(introItems[pagerState.currentPage].buttonText),
            )
            SecondaryButton(
                shape = ButtonShape.Pill,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                text = stringResource(R.string.sign_in),
                onClick = {
                    gotoAuth()
                },
            )
        }

        LaunchedEffect(key1 = pagerState.currentPage) {
            launch {
                delay(2000)
                with(pagerState) {
                    val target = if (currentPage < introItems.count() - 1) currentPage + 1 else 0

                    animateScrollToPage(
                        page = target, animationSpec = tween(
                            durationMillis = 0, easing = FastOutLinearInEasing
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun IntroView(
    itemsList: List<IntroItem> = listOf(),
    pagerState: PagerState,
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { index ->
        DisplayIntro(itemsList[index])
    }
    Spacer(modifier = Modifier.height(20.dp))
    IntroPagerIndicator(itemsList, pagerState)
}

@Composable
fun DisplayIntro(item: IntroItem) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(320.dp)
                .height(320.dp),
            painter = painterResource(item.drawableRes),
            contentDescription = stringResource(item.title)
        )

        Text(
            text = stringResource(item.title),
            style = BazarTheme.typography.headlineSmall,
            color = Color.Black,
            textAlign = TextAlign.Center,
            minLines = 2,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 70.dp)
        )

        Text(
            text = stringResource(item.subtitle),
            style = BazarTheme.typography.bodyLarge,
            color = Color.Gray,
            minLines = 3,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .padding(horizontal = 4.dp, vertical = 2.dp)
        )
    }
}

@Preview
@Composable
private fun OnboardingScreenPreview() {
    BazarTheme {
        OnboardingScreen(
            viewModel = hiltViewModel(),
            gotoAuth = {}
        )
    }
}