package com.example.merchplace.presentation.screens.auctions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.merchplace.presentation.navigation.Screen
import com.example.merchplace.presentation.widgets.Categories
import com.example.merchplace.ui.theme.MerchPlaceTheme

@Composable
fun AuctionsScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    viewModel: AuctionsViewModel = viewModel()
) {
    val auctions by viewModel.auctions.collectAsState()
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Аукционы",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "Найдите уникальные коллекционные предметы",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Categories()
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(auctions) { auction ->
                AuctionCard(
                    auction = auction,
                    onClick = {
                        navController?.navigate(Screen.AuctionDetail.createRoute(auction.id))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        
        Spacer(modifier = Modifier.height(80.dp)) // Space for bottom navigation
    }
}

@Preview(showBackground = true)
@Composable
private fun AuctionsScreenPreview() {
    MerchPlaceTheme {
        AuctionsScreen()
    }
}

