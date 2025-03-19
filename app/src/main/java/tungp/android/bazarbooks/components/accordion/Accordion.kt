package tungp.android.bazarbooks.components.accordion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp



@Composable
fun Accordion(
    title: String,
    isExpanded: Boolean,
    onHeaderClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        // Header section
        AccordionHeader(
            title = title,
            isExpanded = isExpanded,
            onClick = onHeaderClick
        )

        // Body section
        AccordionBody(
            isExpanded = isExpanded,
            content = content
        )
    }
}

@Composable
private fun AccordionHeader(
    title: String,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "rotation"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                modifier = Modifier.rotate(rotationState)
            )
        }
    }
}

@Composable
private fun AccordionBody(
    isExpanded: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(visible = isExpanded) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.small
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                content()
            }
        }
    }
}

data class AccordionItem(
    val title: String,
    val content: @Composable () -> Unit
)

@Composable
fun AccordionGroup(
    items: List<AccordionItem>,
    isAllowOpenAll: Boolean = true,
    initialExpandedIndices: Set<Int> = emptySet(),
    onAccordionClicked: (Int) -> Unit = {}
) {
    var expandedIndices by remember { mutableStateOf(initialExpandedIndices) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items.forEachIndexed { index, item ->
            val isExpanded = expandedIndices.contains(index)

            Accordion(
                title = item.title,
                isExpanded = isExpanded,
                onHeaderClick = {
                    // Update internal state
                    expandedIndices = if (isAllowOpenAll) {
                        // Toggle the clicked accordion
                        if (isExpanded) {
                            expandedIndices - index
                        } else {
                            expandedIndices + index
                        }
                    } else {
                        // Only allow one accordion to be open at a time
                        if (isExpanded) {
                            emptySet() // Close all if clicking on already open accordion
                        } else {
                            setOf(index) // Open only the clicked accordion
                        }
                    }

                    // Call the external callback
                    onAccordionClicked(index)
                }
            ) {
                item.content()
            }

            if (index < items.size - 1) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

// Example usage
@Composable
fun AccordionExample() {
    // Create accordion items
    val accordionItems = listOf(
        AccordionItem(
            title = "Section 1",
            content = { Text("Content for section 1") }
        ),
        AccordionItem(
            title = "Section 2",
            content = { Text("Content for section 2") }
        ),
        AccordionItem(
            title = "Section 3",
            content = { Text("Content for section 3") }
        )
    )

    // Use the AccordionGroup with state management handled internally
    AccordionGroup(
        items = accordionItems,
        isAllowOpenAll = true,
        initialExpandedIndices = setOf(0), // Optional: start with first item expanded
        onAccordionClicked = { index ->
            // Optional: handle click events
            println("Accordion $index was clicked")
        }
    )
}