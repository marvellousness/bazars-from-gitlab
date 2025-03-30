package tungp.android.bazarbooks.components.accordion


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.ui.theme.BazarTheme


// Enum for Accordion type
enum class AccordionType {
    SINGLE, MULTIPLE
}

// Accordion state holder
class AccordionState(
    private val type: AccordionType,
    initialExpandedItems: List<String> = emptyList(),
    private val onValueChange: ((List<String>) -> Unit)? = null
) {
    private val _expandedItems = mutableStateListOf<String>().apply {
        addAll(initialExpandedItems)
    }

    val expandedItems: List<String> get() = _expandedItems

    fun isExpanded(itemId: String): Boolean = itemId in _expandedItems

    fun toggleExpanded(itemId: String) {
        if (itemId in _expandedItems) {
            _expandedItems.remove(itemId)
        } else {
            if (type == AccordionType.SINGLE) {
                _expandedItems.clear()
            }
            _expandedItems.add(itemId)
        }
        onValueChange?.invoke(_expandedItems.toList())
    }
}

@Composable
fun rememberAccordionState(
    type: AccordionType = AccordionType.SINGLE,
    initialExpandedItems: List<String> = emptyList(),
    onValueChange: ((List<String>) -> Unit)? = null
): AccordionState {
    return remember {
        AccordionState(type, initialExpandedItems, onValueChange)
    }
}

@Composable
fun Accordion(
    modifier: Modifier = Modifier,
    type: AccordionType = AccordionType.SINGLE,
    defaultValue: String? = null,
    onValueChange: ((List<String>) -> Unit)? = null,
    state: AccordionState = rememberAccordionState(
        type, 
        initialExpandedItems = defaultValue?.let { listOf(it) } ?: emptyList(),
        onValueChange = onValueChange
    ),
    content: @Composable (AccordionState) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        content(state)
    }
}

@Composable
fun AccordionItem(
    itemId: String,
    title: String,
    state: AccordionState,
    content: @Composable () -> Unit
) {
    val expanded = state.isExpanded(itemId)
    val transition = updateTransition(targetState = expanded, label = "accordionTransition")
    val arrowRotation by transition.animateFloat(
        label = "arrowRotation",
        transitionSpec = { tween(durationMillis = 300) }
    ) { isExpanded ->
        if (isExpanded) 180f else 0f
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        // Accordion Header
        AccordionHeader(
            title = title,
            arrowRotation = arrowRotation,
            expanded = expanded,
            onClick = { state.toggleExpanded(itemId) }
        )

        // Accordion Content
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(animationSpec = tween(300)) + expandVertically(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300)) + shrinkVertically(animationSpec = tween(300))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .padding(16.dp)
            ) {
                content()
            }
        }

        HorizontalDivider()
    }
}

@Composable
private fun AccordionHeader(
    title: String,
    arrowRotation: Float,
    expanded: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = if (expanded) "Collapse" else "Expand",
            modifier = Modifier.rotate(arrowRotation)
        )
    }
}

// Example usage
@Composable
fun AccordionDemo() {
    BazarTheme {
        Column {
            // Single selection mode (default)
            Accordion(
                type = AccordionType.SINGLE,
                onValueChange = { expandedItems ->
                    // Handle expanded items change
                    // For SINGLE type, this list will contain 0 or 1 item
                    val expandedItem = expandedItems.firstOrNull()
                    Log.d("TAG", "AccordionDemo: expandedItem=$expandedItem")
                    if (expandedItem != null) {
                        // An item was expanded
                    } else {
                        // All items were collapsed
                    }
                }
            ) { state ->
                AccordionItem(
                    itemId = "item-1",
                    title = "Is it accessible?",
                    state = state
                ) {
                    Text("Yes. It follows Material Design accessibility guidelines.")
                }

                AccordionItem(
                    itemId = "item-2",
                    title = "Is it styled?",
                    state = state
                ) {
                    Text("Yes. It uses Material Design components and follows the theme.")
                }

                AccordionItem(
                    itemId = "item-3",
                    title = "Is it animated?",
                    state = state
                ) {
                    Text("Yes. It includes smooth expand/collapse animations by default.")
                }
            }

            HorizontalDivider(Modifier.height(40.dp))

            // Multiple selection mode example
            Accordion(
                type = AccordionType.MULTIPLE,
                onValueChange = { expandedItems ->
                    // Handle multiple expanded items
                    Log.d("TAG", "AccordionDemo: expandedItems=$expandedItems")
                },
                defaultValue = "multi-2"
            ) { state ->
                AccordionItem(
                    itemId = "multi-1",
                    title = "Can multiple items be open?",
                    state = state
                ) {
                    Text("Yes. In MULTIPLE mode, any number of items can be open simultaneously.")
                }

                AccordionItem(
                    itemId = "multi-2",
                    title = "How do I control the state?",
                    state = state
                ) {
                    Text("The AccordionState manages which items are expanded. You can also provide initial expanded items.")
                }

                AccordionItem(
                    itemId = "multi-3",
                    title = "Can I customize the appearance?", 
                    state = state
                ) {
                    Text("Yes, you can customize colors, animations and other visual aspects by modifying the component.")
                }
            }
        }
    }
}