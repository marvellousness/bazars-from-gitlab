package tungp.android.bazarbooks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.R
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.ThemedPreview

/**
 * Style options for the rating stars
 */
enum class RatingStarStyle {
    FILL,    // Filled stars with color
    STROKE,  // Outline stars
    DEFAULT  // System default style
}

/**
 * A reusable component to display a star rating
 * 
 * @param value The current rating value
 * @param numberOfStars The total number of stars to display
 * @param size The size of each star
 * @param spaceBetween The spacing between stars
 * @param style The visual style of the stars (FILL, STROKE, or DEFAULT)
 * @param filledStarColor The color of filled stars
 * @param emptyStarColor The color of empty stars
 * @param filledStarPainter Custom painter for filled stars
 * @param emptyStarPainter Custom painter for empty stars
 * @param modifier Additional modifier for the component
 */
@Composable
fun RatingDisplay(
    value: Int,
    numberOfStars: Int = 5,
    size: Dp = 16.dp,
    spaceBetween: Dp = 2.dp,
    style: RatingStarStyle = RatingStarStyle.FILL,
    filledStarColor: Color = BazarTheme.extendedColors.yellow,
    emptyStarColor: Color = BazarTheme.colors.tertiary,
    filledStarPainter: Painter = painterResource(id = R.drawable.ic_star_filled),
    emptyStarPainter: Painter = painterResource(id = R.drawable.ic_star_outline),
    modifier: Modifier = Modifier
) {
    val boundedRating = value.coerceIn(0, numberOfStars)
    
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Filled stars
        repeat(boundedRating) {
            when (style) {
                RatingStarStyle.DEFAULT -> {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = filledStarColor,
                        modifier = Modifier.size(size)
                    )
                }
                else -> {
                    Image(
                        painter = filledStarPainter,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(filledStarColor),
                        modifier = Modifier.size(size)
                    )
                }
            }
        }
        
        // Empty stars
        repeat(numberOfStars - boundedRating) {
            when (style) {
                RatingStarStyle.DEFAULT -> {
                    Icon(
                        imageVector = Icons.Filled.StarBorder,
                        contentDescription = null,
                        tint = emptyStarColor,
                        modifier = Modifier.size(size)
                    )
                }
                else -> {
                    Image(
                        painter = emptyStarPainter,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(emptyStarColor),
                        modifier = Modifier.size(size)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingStarsDefaultPreview() {
    ThemedPreview {
        RatingDisplay(
            value = 2,
            style = RatingStarStyle.FILL
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingStarsCustomPreview() {
    ThemedPreview {
        RatingDisplay(
            value = 4,
            numberOfStars = 6,
            size = 24.dp,
            spaceBetween = 4.dp,
            style = RatingStarStyle.FILL,
            filledStarColor = BazarTheme.extendedColors.yellow,
            emptyStarColor = BazarTheme.colors.outline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingStarsStrokePreview() {
    ThemedPreview {
        RatingDisplay(
            value = 2,
            style = RatingStarStyle.STROKE,
            filledStarColor = BazarTheme.extendedColors.green,
            emptyStarColor = BazarTheme.extendedColors.greenContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingStarsZeroPreview() {
    ThemedPreview {
        RatingDisplay(
            value = 0,
            style = RatingStarStyle.DEFAULT
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingStarsFullPreview() {
    ThemedPreview {
        RatingDisplay(
            value = 5,
            style = RatingStarStyle.DEFAULT
        )
    }
} 