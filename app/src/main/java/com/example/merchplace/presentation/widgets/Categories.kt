package com.example.merchplace.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchplace.ui.theme.MerchPlaceTheme
import com.example.merchplace.ui.theme.PrimaryRed

val categories = listOf(
    "Все",
    "Фигурки",
    "Арт",
    "Одежда",
    "Аксессуары",
    "Книги",
    "Игрушки"
)

@Composable
fun Categories(
    selectedCategory: String = "Все",
    onCategorySelected: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            CategoryChip(
                text = category,
                isSelected = category == selectedCategory,
                onClick = { onCategorySelected(category) }
            )
        }
    }
}

@Composable
private fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected) PrimaryRed.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
            )
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = if (isSelected) PrimaryRed.copy(alpha = 0.3f) else MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isSelected) PrimaryRed else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoriesPreview() {
    MerchPlaceTheme {
        Categories()
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryChipPreview() {
    MerchPlaceTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CategoryChip(
                text = "Все",
                isSelected = true,
                onClick = {}
            )
            CategoryChip(
                text = "Фигурки",
                isSelected = false,
                onClick = {}
            )
        }
    }
}

