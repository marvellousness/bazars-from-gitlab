package tungp.android.bazarbooks.screens.order.status

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.components.BazarSurface
import tungp.android.bazarbooks.components.RatingDisplay
import tungp.android.bazarbooks.components.RatingStarStyle
import tungp.android.bazarbooks.components.button.PrimaryButton
import tungp.android.bazarbooks.navigation.AuthRouteScreen
import tungp.android.bazarbooks.navigation.CartRouteScreen
import tungp.android.bazarbooks.navigation.Graph
import tungp.android.bazarbooks.navigation.MainRouteScreen
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.GrayScale500
import tungp.android.bazarbooks.ui.theme.GrayScale900
import tungp.android.bazarbooks.ui.theme.Primary500
import tungp.android.bazarbooks.ui.theme.Primary900
import tungp.android.bazarbooks.ui.theme.ThemedPreview
import tungp.android.bazarbooks.ui.theme.paddingDefault

@Composable
fun OrderRatingScreen(navController: NavController) {
    OrderRatingContainer(onDone = {
        navController.navigate(MainRouteScreen.Home.route) {
            popUpTo(Graph.CartGraph) {
                inclusive = true
            }
            launchSingleTop = true
        }
    })
}

@Composable
fun OrderRatingContainer(onDone: () -> Unit) {
    BazarSurface(backgroundColor = BazarTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paddingDefault()
                .verticalScroll(rememberScrollState()),
        ) {
            RatingHeader()
            RatingBody()
            RatingActionSection(onDone = onDone)
        }
    }
}

@Composable
fun RatingHeader(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(height = 40.dp))
        Image(
            painter = painterResource(id = R.drawable.order_congratulation),
            contentDescription = null,
            modifier = Modifier
                .requiredWidth(width = 160.dp)
                .requiredHeight(height = 91.dp)
        )
        Text(
            text = "You Received The Order!",
            color = GrayScale900,
            textAlign = TextAlign.Center,
            style = BazarTheme.typography.headlineSmall,
        )
        Text(
            text = "Order #2930541",
            color = GrayScale500,
            textAlign = TextAlign.Center,
            style = BazarTheme.typography.bodyLarge,

            )
    }
}

@Composable
fun RatingBody(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.requiredHeight(height = 30.dp))
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(BazarTheme.colors.surface)
            .paddingDefault()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tell us your feedback 🙌",
                color = Primary500,
                textAlign = TextAlign.Center,
                style = BazarTheme.typography.titleLarge
            )
            Text(
                text = "Lorem ipsum dolor sit amet consectetur.\nDignissim magna vitae.",
                color = Primary500,
                textAlign = TextAlign.Center,
                style = BazarTheme.typography.bodyMedium,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
        ) {
            RatingDisplay(
                value = 2,
                style = RatingStarStyle.FILL,
                size = 32.dp
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Write something for us!",
            color = Primary900,
            textAlign = TextAlign.Center,
            style = BazarTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun RatingActionSection(onDone: () -> Unit) {
    Spacer(modifier = Modifier.requiredHeight(height = 30.dp))
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        PrimaryButton(
            text = "Done",
            modifier = Modifier.fillMaxWidth(),
            onClick = onDone
        )
    }
}


@Preview
@Composable
private fun OrderRatingContainerPreview() {
    ThemedPreview {
        OrderRatingContainer(onDone = {})
    }
}
