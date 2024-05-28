package com.example.logique_test.presenter.navigation

import androidx.navigation.NavHostController
import com.example.logique_test.domain.uimodel.UserUiModel

class Actions(private val navHostController: NavHostController) {
    val home: () -> Unit = { navHostController.navigate(Routes.HOME) }

    val favorite: () -> Unit = { navHostController.navigate(Routes.FAVORITE) }

    fun user(userId: String) { navHostController.navigate("${Routes.USER}/$userId") }
}