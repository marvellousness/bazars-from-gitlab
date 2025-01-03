package tungp.android.bazarbooks.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.ui.theme.BazarTheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    shape: ButtonShape = ButtonShape.Rounded,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape.shape
    ) {
        icon?.let { painter ->
            Icon(
                painter = painter,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = text)
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    shape: ButtonShape = ButtonShape.Rounded,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape.shape,
    ) {
        icon?.let { painter ->
            Icon(
                painter = painter,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = text)
    }
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        icon?.let { painter ->
            Icon(
                painter = painter,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = text)
    }
}

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    icon: Painter? = null,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        icon?.let { painter ->
            Icon(
                painter = painter,
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = text)
    }
}

@Composable
fun SignInButton(
    text: String,
    painter: Painter,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.clickable(onClick = onClick),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = BazarTheme.colors.surface,
        shape = ButtonShape.Rounded.shape,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .fillMaxWidth()
        ) {
            Icon(
                painter = painter,
                contentDescription = "Google Login",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)

            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Composable
@Preview
private fun ButtonPreview() {
    BazarTheme {
        Column(
            modifier = Modifier
                .background(BazarTheme.colors.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            PrimaryButton(
                text = "Primary Button",
                icon = painterResource(R.drawable.ic_apple_original),
                onClick = {}
            )

            SecondaryButton(
                text = "Secondary Button",
                icon = painterResource(R.drawable.ic_apple_original),
                onClick = {}
            )

            TextButton(
                text = "Text Button",
                icon = painterResource(R.drawable.ic_apple_original),
                onClick = {}
            )

            OutlinedButton(
                text = "Sign in with Google",
                icon = painterResource(R.drawable.ic_google_original),
                onClick = {}
            )

            SignInButton(
                text = "Sign in With Google",
                painter = painterResource(id = R.drawable.ic_google_original),
            ) { }
        }
    }
}