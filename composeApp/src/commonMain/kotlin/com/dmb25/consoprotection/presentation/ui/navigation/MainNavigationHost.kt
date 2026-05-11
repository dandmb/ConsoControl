package com.dmb25.consoprotection.presentation.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.dmb25.consoprotection.presentation.ui.product_detail.ProductDetailsScreen
import com.dmb25.consoprotection.presentation.ui.product_list.ProductListScreen

@Composable
fun MainNavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = MainNavigationDestination.Home,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<MainNavigationDestination.Home> {
            ProductListScreen(
                onProductClick = {
                    navController.navigate(MainNavigationDestination.Details(it))
                }
            )
        }
        composable<MainNavigationDestination.Details> {
            val route = it.toRoute<MainNavigationDestination.Details>()
            ProductDetailsScreen(
                productId = route.productId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}