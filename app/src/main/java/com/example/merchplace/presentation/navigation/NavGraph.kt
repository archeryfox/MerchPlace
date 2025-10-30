package com.example.merchplace.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.merchplace.presentation.screens.auctions.AuctionDetailScreen
import com.example.merchplace.presentation.screens.auctions.AuctionsScreen
import com.example.merchplace.presentation.screens.cart.CartScreen
import com.example.merchplace.presentation.screens.favorites.FavoritesScreen
import com.example.merchplace.presentation.screens.feed.FeedScreen
import com.example.merchplace.presentation.screens.home.HomeScreen
import com.example.merchplace.presentation.screens.lotteries.LotteriesScreen
import com.example.merchplace.presentation.screens.map.MapScreen
import com.example.merchplace.presentation.screens.notifications.NotificationsScreen
import com.example.merchplace.presentation.screens.profile.ProfileScreen
import com.example.merchplace.presentation.screens.shop.ProductDetailScreen
import com.example.merchplace.presentation.screens.shop.ShopScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Auctions.route) {
            AuctionsScreen(navController = navController)
        }
        composable(
            route = Screen.AuctionDetail.routeWithArgs,
            arguments = listOf(
                navArgument("auctionId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val auctionId = backStackEntry.arguments?.getInt("auctionId") ?: 0
            AuctionDetailScreen(
                auctionId = auctionId,
                navController = navController
            )
        }
        composable(Screen.Shop.route) {
            ShopScreen(navController = navController)
        }
        composable(
            route = Screen.ProductDetail.routeWithArgs,
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(
                productId = productId,
                navController = navController
            )
        }
        composable(Screen.Map.route) {
            MapScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.Cart.route) {
            CartScreen(navController = navController)
        }
        composable(Screen.Feed.route) {
            FeedScreen()
        }
        composable(Screen.Lotteries.route) {
            LotteriesScreen()
        }
        composable(Screen.Notifications.route) {
            NotificationsScreen()
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Auctions : Screen("auctions")
    object Shop : Screen("shop")
    object Map : Screen("map")
    object Profile : Screen("profile")
    object Cart : Screen("cart")
    object Feed : Screen("feed")
    object Lotteries : Screen("lotteries")
    object Notifications : Screen("notifications")
    object Favorites : Screen("favorites")
    object ProductDetail : Screen("product/{productId}") {
        const val routeWithArgs = "product/{productId}"
        fun createRoute(productId: Int) = "product/$productId"
    }
    object AuctionDetail : Screen("auction/{auctionId}") {
        const val routeWithArgs = "auction/{auctionId}"
        fun createRoute(auctionId: Int) = "auction/$auctionId"
    }
}

