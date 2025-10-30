package com.example.merchplace.shared.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchplace.ui.theme.DarkBorder
import com.example.merchplace.ui.theme.DarkCard
import com.example.merchplace.ui.theme.MerchPlaceTheme
import com.example.merchplace.ui.theme.TextPrimary

@Composable
fun MerchCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) {
                    Modifier.clickable(onClick = onClick)
                } else {
                    Modifier
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = DarkCard
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = DarkBorder,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            content = content
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MerchCardPreview() {
    MerchPlaceTheme {
        MerchCard(
            modifier = Modifier.padding(16.dp),
            onClick = {}
        ) {
            Text(
                text = "Пример карточки",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "Это пример содержимого карточки",
                fontSize = 14.sp,
                color = TextPrimary
            )
        }
    }
}

