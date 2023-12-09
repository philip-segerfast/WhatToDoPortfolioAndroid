package segerfast.philip.whattodo.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : Screen("home")

    data object TodoDetail : Screen(
        route = "todoDetail/{todoId}",
        navArguments = listOf(navArgument("todoId") {
            type = NavType.IntType
        })
    ) {
        fun createRoute(todoId: String) = "todoDetail/${todoId}"
    }

}