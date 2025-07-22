package tungp.android.bazarbooks.screens.vendor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.components.RatingStarStyle
import tungp.android.bazarbooks.components.RatingDisplay
import tungp.android.bazarbooks.ui.theme.BazarTheme
import tungp.android.bazarbooks.ui.theme.ThemedPreview

/**
 * Demonstration screen showcasing various configurations of the RatingStars component
 */
@Composable
fun RatingStarsDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = "Rating Stars Component",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        // Different styles
        DemoSection(title = "Styles") {
            RatingStarStyleRow(value = 4)
        }
        
        // Different values
        DemoSection(title = "Values") {
            RatingValuesRow()
        }
        
        // Different sizes
        DemoSection(title = "Sizes") {
            RatingSizesRow()
        }
        
        // Different numbers of stars
        DemoSection(title = "Number of Stars") {
            RatingStarsCountRow()
        }
        
        // Different colors
        DemoSection(title = "Colors") {
            RatingColorsRow()
        }
        
        // Combination examples
        DemoSection(title = "Example Combinations") {
            RatingExamplesColumn()
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun DemoSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )
        
        content()
    }
}

@Composable
fun RatingStarStyleRow(value: Int) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RatingStyleItem(
            title = "DEFAULT style",
            description = "Uses Material Icons",
            style = RatingStarStyle.DEFAULT,
            value = value
        )
        
        RatingStyleItem(
            title = "FILL style",
            description = "Uses custom drawables with ColorFilter",
            style = RatingStarStyle.FILL,
            value = value
        )
        
        RatingStyleItem(
            title = "STROKE style",
            description = "Also uses custom drawables",
            style = RatingStarStyle.STROKE,
            value = value
        )
    }
}

@Composable
fun RatingStyleItem(
    title: String,
    description: String,
    style: RatingStarStyle,
    value: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        RatingDisplay(
            value = value,
            style = style
        )
    }
}

@Composable
fun RatingValuesRow() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (value in 0..5) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Value: $value",
                    style = MaterialTheme.typography.bodyMedium
                )

                RatingDisplay(
                    value = value,
                    style = RatingStarStyle.FILL
                )
            }
            
            if (value < 5) {
                Divider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                )
            }
        }
    }
}

@Composable
fun RatingSizesRow() {
    val sizes = listOf(
        8.dp to "Small (8dp)",
        12.dp to "Medium (12dp)",
        16.dp to "Default (16dp)",
        24.dp to "Large (24dp)",
        32.dp to "Extra Large (32dp)"
    )
    
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        sizes.forEach { (size, label) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )

                RatingDisplay(
                    value = 3,
                    size = size,
                    style = RatingStarStyle.FILL
                )
            }
        }
    }
}

@Composable
fun RatingStarsCountRow() {
    val counts = listOf(3, 5, 7, 10)
    
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        counts.forEach { count ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$count stars",
                    style = MaterialTheme.typography.bodyMedium
                )

                RatingDisplay(
                    value = count / 2,
                    numberOfStars = count,
                    style = RatingStarStyle.FILL,
                    size = 14.dp
                )
            }
        }
    }
}

@Composable
fun RatingColorsRow() {
    val colorPairs = listOf(
        Triple("Yellow (Default)", BazarTheme.extendedColors.yellow, BazarTheme.colors.outline),
        Triple("Green", BazarTheme.extendedColors.green, BazarTheme.extendedColors.greenContainer),
        Triple("Primary", BazarTheme.colors.primary, BazarTheme.colors.outlineVariant),
        Triple("Custom", Color(0xFFFF6D00), Color(0xFFFFE0B2))
    )
    
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        colorPairs.forEach { (label, filledColor, emptyColor) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium
                )

                RatingDisplay(
                    value = 3,
                    filledStarColor = filledColor,
                    emptyStarColor = emptyColor,
                    style = RatingStarStyle.DEFAULT
                )
            }
        }
    }
}

@Composable
fun RatingExamplesColumn() {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Product Rating",
                    style = MaterialTheme.typography.titleMedium
                )

                RatingDisplay(
                    value = 4,
                    numberOfStars = 5,
                    size = 24.dp,
                    spaceBetween = 4.dp,
                    style = RatingStarStyle.FILL,
                    filledStarColor = BazarTheme.extendedColors.yellow
                )
                
                Text(
                    text = "4.0 (243 reviews)",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Difficulty Level",
                    style = MaterialTheme.typography.titleMedium
                )

                RatingDisplay(
                    value = 3,
                    numberOfStars = 3,
                    size = 32.dp,
                    spaceBetween = 8.dp,
                    style = RatingStarStyle.FILL,
                    filledStarColor = BazarTheme.extendedColors.green
                )
                
                Text(
                    text = "Intermediate",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingStarsDemoPreview() {
    ThemedPreview {
        RatingStarsDemo()
    }
} 