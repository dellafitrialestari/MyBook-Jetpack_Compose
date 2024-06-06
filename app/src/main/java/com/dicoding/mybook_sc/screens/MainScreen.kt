package com.dicoding.mybook_sc.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.mybook_sc.screens.aboutme.AboutMeScreen
import com.dicoding.mybook_sc.screens.detail.DetailScreen
import com.dicoding.mybook_sc.screens.home.HomeScreen

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        ) {
        composable(Routes.Home.route) {
            HomeScreen(
                navigateToDetail = { id ->
                    navController.navigate(Routes.Detail.createRoute(id))
                                   },
                navController = navController
            )
        }

        composable(
            Routes.Detail.route,
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })
        ) {
            val id = it.arguments?.getLong("id") ?: -1L
            DetailScreen(
                id = id,
                navigateBack = {
                    navController.navigateUp()
                               },
                )
        }

        composable(Routes.Profile.route) {
            AboutMeScreen(
                onBackClick = {
                    navController.navigateUp()
                              },
            )
        }
    }
}