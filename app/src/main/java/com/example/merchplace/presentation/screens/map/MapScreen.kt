package com.example.merchplace.presentation.screens.map

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.merchplace.domain.entities.Booth
import com.example.merchplace.shared.ui.LocalAsyncImage
import com.example.merchplace.shared.ui.MerchCard
import com.example.merchplace.ui.theme.MerchPlaceTheme
import com.example.merchplace.ui.theme.PrimaryRed

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = viewModel()
) {
    val booths by viewModel.booths.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val selectedBooth by viewModel.selectedBooth.collectAsState()
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Карта мероприятия",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (booths.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Нет доступных стендов",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                SimpleMapView(
                    booths = booths,
                    onBoothClick = { booth ->
                        viewModel.selectBooth(booth)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            
            if (selectedBooth != null) {
                Spacer(modifier = Modifier.height(16.dp))
                SelectedBoothCard(
                    booth = selectedBooth!!,
                    onDismiss = { viewModel.selectBooth(null) }
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Список стендов",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            booths.forEach { booth ->
                BoothListItem(
                    booth = booth,
                    onClick = { viewModel.selectBooth(booth) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
private fun SimpleMapView(
    booths: List<Booth>,
    onBoothClick: (Booth) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()
        
        if (booths.isNotEmpty()) {
            val minLat = booths.minOf { it.latitude }
            val maxLat = booths.maxOf { it.latitude }
            val minLon = booths.minOf { it.longitude }
            val maxLon = booths.maxOf { it.longitude }
            
            val latRange = (maxLat - minLat).coerceAtLeast(0.0001)
            val lonRange = (maxLon - minLon).coerceAtLeast(0.0001)
            
            val surfaceColor = androidx.compose.ui.graphics.Color(0xFF151F23)
            
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRect(
                    color = surfaceColor,
                    topLeft = Offset(0f, 0f),
                    size = androidx.compose.ui.geometry.Size(size.width, size.height)
                )
            }
            
            booths.forEach { booth ->
                val x = ((booth.longitude - minLon) / lonRange) * width
                val y = height - ((booth.latitude - minLat) / latRange) * height
                
                Box(
                    modifier = Modifier
                        .offset(x = (x - 16).dp, y = (y - 16).dp)
                        .size(32.dp)
                        .clickable { onBoothClick(booth) }
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = booth.name,
                        tint = PrimaryRed,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        } else {
            val surfaceColor = androidx.compose.ui.graphics.Color(0xFF151F23)
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawRect(
                    color = surfaceColor,
                    topLeft = Offset(0f, 0f),
                    size = androidx.compose.ui.geometry.Size(size.width, size.height)
                )
            }
        }
    }
}

@Composable
private fun SelectedBoothCard(
    booth: Booth,
    onDismiss: () -> Unit
) {
    MerchCard {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = booth.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Закрыть"
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LocalAsyncImage(
                    imageUrl = booth.seller.avatar,
                    contentDescription = booth.seller.name,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = booth.seller.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = booth.category,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun BoothListItem(
    booth: Booth,
    onClick: () -> Unit
) {
    MerchCard(
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = PrimaryRed,
                modifier = Modifier.size(32.dp)
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = booth.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = booth.seller.name,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Text(
                text = booth.category,
                fontSize = 12.sp,
                color = PrimaryRed,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MapScreenPreview() {
    MerchPlaceTheme {
        MapScreen()
    }
}
