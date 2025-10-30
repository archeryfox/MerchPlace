package com.example.merchplace.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.merchplace.presentation.widgets.Categories
import com.example.merchplace.presentation.widgets.FeaturedAuctions
import com.example.merchplace.presentation.widgets.Hero
import com.example.merchplace.presentation.widgets.Stats
import com.example.merchplace.ui.theme.MerchPlaceTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Hero(modifier = Modifier.fillMaxWidth(), navController = navController)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Stats(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Categories(
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        FeaturedAuctions(
            modifier = Modifier.fillMaxWidth(),
            navController = navController
        )
        
        Spacer(modifier = Modifier.height(80.dp)) // Space for bottom navigation
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MerchPlaceTheme {
        HomeScreen()
    }
}

