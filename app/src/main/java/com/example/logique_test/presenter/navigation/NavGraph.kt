package com.example.logique_test.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logique_test.presenter.page.favorite.FavoritePage
import com.example.logique_test.presenter.page.user.UserPage
import com.example.logique_test.presenter.page.home.HomePage

@Composable
fun NavGraph(startDestination: String = Routes.HOME) {
    val navController = rememberNavController()

    val actions = remember(navController) { Actions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.HOME) {
            HomePage(actions)
        }
        composable("${Routes.USER}/{userId}") {
            val userId = it.arguments?.getString("userId") ?: ""
            UserPage(
                userId = userId,
                actions = actions
            )
        }
        composable(Routes.FAVORITE) {
            FavoritePage()
        }
    }
}