package segerfast.philip.whattodo.compose

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import segerfast.philip.whattodo.compose.overview.OverviewScreen

@Composable
fun WhatDoToApp() {
    val navController = rememberNavController()
    WhatToDoNavHost(navController = navController)
}

@Composable
fun WhatToDoNavHost(navController: NavHostController) {
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            OverviewScreen()
        }
        composable(
            route = Screen.TodoDetail.route,
            arguments = Screen.TodoDetail.navArguments
        ) {
            Text("Todo-details")
//            PlantDetailsScreen(
//                onBackClick = { navController.navigateUp() },
//                onShareClick = {
//                    createShareIntent(activity, it)
//                },
//                onGalleryClick = {
//                    navController.navigate(
//                        Screen.Gallery.createRoute(
//                            plantName = it.name
//                        )
//                    )
//                }
//            )
        }
    }
}