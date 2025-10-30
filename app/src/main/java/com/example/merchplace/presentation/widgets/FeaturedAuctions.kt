package com.example.merchplace.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.merchplace.data.datasource.mock.MockAuctions
import com.example.merchplace.domain.entities.Auction
import com.example.merchplace.presentation.navigation.Screen
import com.example.merchplace.presentation.screens.auctions.AuctionCard
import com.example.merchplace.presentation.screens.auctions.AuctionsViewModel
import com.example.merchplace.ui.theme.MerchPlaceTheme

@Composable
fun FeaturedAuctions(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    viewModel: AuctionsViewModel = viewModel()
) {
    val auctions by viewModel.auctions.collectAsState()
    
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                text = "Рекомендуемые аукционы",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(auctions.take(5)) { auction ->
                AuctionCard(
                    auction = auction,
                    modifier = Modifier.width(280.dp),
                    onClick = {
                        navController?.navigate(Screen.AuctionDetail.createRoute(auction.id))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FeaturedAuctionsPreview() {
    MerchPlaceTheme {
        // Mock preview without ViewModel
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = "Рекомендуемые аукционы",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(MockAuctions.auctions.take(3)) { auction ->
                    AuctionCard(
                        auction = auction,
                        modifier = Modifier.width(280.dp),
                        onClick = {}
                    )
                }
            }
        }
    }
}

