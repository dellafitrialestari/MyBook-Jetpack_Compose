package com.dicoding.mybook_sc.screens

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Detail : Routes("detail/{id}") {
        fun createRoute(id: Long) = "detail/$id"
    }
    object Profile : Routes("profile")
}