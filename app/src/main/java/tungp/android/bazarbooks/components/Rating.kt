package tungp.android.bazarbooks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import tungp.android.bazarbooks.R

@Composable
fun Rating(
    rating: Int,
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    onRatingChanged: (Int) -> Unit,
) {
    require(maxRating > 0) { "maxRating must be greater than 0" }
    require(rating in 0..maxRating) { "rating must be between 0 and maxRating" }

    var selectedRating by remember(rating) { mutableIntStateOf(rating) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        for (index in 1..maxRating) {
            val isFilled = index <= selectedRating
            Icon(
                imageVector = if (isFilled) Icons.Filled.Star else Icons.Filled.StarBorder,
                contentDescription = if (isFilled) stringResource(R.string.filled_star) else stringResource(
                    R.string.empty_star
                ),
                tint = if (isFilled) Color(0xFFFFC107) else Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        selectedRating = index
                        onRatingChanged(index)
                    }
                    .semantics {
                        val description = "$index out of $maxRating"
                        contentDescription = description
                        onClick {
                            selectedRating = index
                            onRatingChanged(index)
                            true
                        }
                    }
            )
        }
        Text(
            text = " $selectedRating/$maxRating",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}