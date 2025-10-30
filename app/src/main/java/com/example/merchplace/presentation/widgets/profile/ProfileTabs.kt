package com.example.merchplace.presentation.widgets.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.merchplace.presentation.navigation.Screen
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.merchplace.data.datasource.mock.MockUsers
import com.example.merchplace.domain.entities.User
import com.example.merchplace.domain.repository.AuctionRepository
import com.example.merchplace.domain.repository.ProductRepository
import com.example.merchplace.presentation.screens.auctions.AuctionCard
import com.example.merchplace.presentation.screens.auctions.AuctionsViewModel
import com.example.merchplace.shared.di.RepositoryProvider
import com.example.merchplace.ui.theme.MerchPlaceTheme

@Composable
fun ProfileTabs(
    user: User,
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    auctionRepository: AuctionRepository = RepositoryProvider.auctionRepository,
    productRepository: ProductRepository = RepositoryProvider.productRepository
) {
    var selectedTab by remember { mutableStateOf(0) }
    
    // Load user's auctions and products through ViewModels
    val auctionsViewModel: AuctionsViewModel = viewModel()
    val auctions by auctionsViewModel.auctions.collectAsState()
    
    val shopViewModel: com.example.merchplace.presentation.screens.shop.ShopViewModel = viewModel()
    val products by shopViewModel.products.collectAsState()
    
    // Reset tab if selected tab doesn't exist for current user
    LaunchedEffect(user.donationEnabled) {
        if (!user.donationEnabled && selectedTab == 2) {
            selectedTab = 0
        }
    }
    
    // Load data when user changes
    LaunchedEffect(user.id) {
        auctionsViewModel.loadAuctions()
        shopViewModel.loadProducts()
    }
    
    // Filter user's data
    val userAuctions = auctions.filter { it.seller.id == user.id }
    val userProducts = products.filter { it.seller.id == user.id }
    
    Column(modifier = modifier) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("Аукционы (${userAuctions.size})") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("Магазин (${userProducts.size})") }
            )
            if (user.donationEnabled) {
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("Поддержать") }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        when (selectedTab) {
            0 -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    userAuctions.forEach { auction ->
                        AuctionCard(
                            auction = auction,
                            onClick = {
                                navController?.navigate(Screen.AuctionDetail.createRoute(auction.id))
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    if (userAuctions.isEmpty()) {
                        Text(
                            text = "Нет аукционов",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
            1 -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Товары магазина будут здесь",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            2 -> {
                Column {
                    Text(
                        text = "Поддержка создателя",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Цель: ${user.donationGoal}₽",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Собрано: ${user.donationCurrent}₽",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileTabsPreview() {
    MerchPlaceTheme {
        ProfileTabs(user = MockUsers.users.first())
    }
}

