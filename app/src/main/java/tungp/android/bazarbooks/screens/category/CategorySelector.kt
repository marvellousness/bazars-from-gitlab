package tungp.android.bazarbooks.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tungp.android.bazarbooks.domain.model.Category
import tungp.android.bazarbooks.ui.theme.BazarTheme

data class CategoryWithSelection(
    val id: String,
    val name: String,
    val isSelected: Boolean = false
)

@Composable
fun CategorySelector(
    categories: List<Category>,
    selectedCategoryId: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val allCategories = remember(categories, selectedCategoryId) {
        listOf(
            CategoryWithSelection(
                id = "all",
                name = "All",
                isSelected = selectedCategoryId == "all"
            )
        ) + categories.map { category ->
            CategoryWithSelection(
                id = category.id,
                name = category.name,
                isSelected = category.id == selectedCategoryId
            )
        }
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(allCategories) { category ->
            CategoryChip(
                category = category,
                onCategorySelected = onCategorySelected
            )
        }
    }
}

@Composable
fun CategoryChip(
    category: CategoryWithSelection,
    onCategorySelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (category.isSelected) BazarTheme.colors.primary
                else BazarTheme.colors.outline
            )
            .clickable { onCategorySelected(category.id) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = category.name,
            fontSize = 14.sp,
            fontWeight = if (category.isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (category.isSelected) Color.White else Color(0xFF757575),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategorySelectorPreview() {
    val previewCategories = listOf(
        Category(id = "cat-001", name = "Fiction", bookCount = 120),
        Category(id = "cat-002", name = "Non-Fiction", bookCount = 85),
        Category(id = "cat-003", name = "Science Fiction", bookCount = 42),
        Category(id = "cat-004", name = "Mystery", bookCount = 63),
        Category(id = "cat-005", name = "Biography", bookCount = 31)
    )

    CategorySelector(
        categories = previewCategories,
        selectedCategoryId = "cat-002",
        onCategorySelected = {}
    )
} 