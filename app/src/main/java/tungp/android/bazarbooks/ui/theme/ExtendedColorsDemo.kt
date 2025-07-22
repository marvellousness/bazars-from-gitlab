package tungp.android.bazarbooks.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Demonstrates the yellow and green extended color families
 */
@Composable
fun ExtendedColorsDemo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Extended Material Colors",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        // Yellow colors
        ColorCategorySection(
            title = "Yellow",
            colors = listOf(
                ColorItem("Yellow", BazarTheme.extendedColors.yellow),
                ColorItem("On Yellow", BazarTheme.extendedColors.onYellow),
                ColorItem("Yellow Container", BazarTheme.extendedColors.yellowContainer),
                ColorItem("On Yellow Container", BazarTheme.extendedColors.onYellowContainer)
            )
        )
        
        // Green colors
        ColorCategorySection(
            title = "Green",
            colors = listOf(
                ColorItem("Green", BazarTheme.extendedColors.green),
                ColorItem("On Green", BazarTheme.extendedColors.onGreen),
                ColorItem("Green Container", BazarTheme.extendedColors.greenContainer),
                ColorItem("On Green Container", BazarTheme.extendedColors.onGreenContainer)
            )
        )
        
        // Yellow shades
        ColorShadesSection(
            title = "Yellow Shades",
            colors = listOf(
                ColorItem("50", Yellow50),
                ColorItem("100", Yellow100),
                ColorItem("200", Yellow200),
                ColorItem("300", Yellow300),
                ColorItem("400", Yellow400),
                ColorItem("500", Yellow500),
                ColorItem("600", Yellow600),
                ColorItem("700", Yellow700),
                ColorItem("800", Yellow800),
                ColorItem("900", Yellow900)
            )
        )
        
        // Green shades
        ColorShadesSection(
            title = "Green Shades",
            colors = listOf(
                ColorItem("50", Green50),
                ColorItem("100", Green100),
                ColorItem("200", Green200),
                ColorItem("300", Green300),
                ColorItem("400", Green400),
                ColorItem("500", Green500),
                ColorItem("600", Green600),
                ColorItem("700", Green700),
                ColorItem("800", Green800),
                ColorItem("900", Green900)
            )
        )
    }
}

@Composable
fun ColorCategorySection(
    title: String,
    colors: List<ColorItem>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            colors.forEach { colorItem ->
                ColorCard(
                    name = colorItem.name,
                    color = colorItem.color,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ColorShadesSection(
    title: String,
    colors: List<ColorItem>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            colors.forEach { colorItem ->
                ColorBox(
                    name = colorItem.name,
                    color = colorItem.color,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ColorCard(
    name: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                // Empty box for color display
            }
            
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ColorBox(
    name: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color)
        )
        
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

data class ColorItem(
    val name: String,
    val color: Color
)

@Preview(showBackground = true)
@Composable
fun ExtendedColorsDemoPreview() {
    ThemedPreview {
        ExtendedColorsDemo()
    }
} 