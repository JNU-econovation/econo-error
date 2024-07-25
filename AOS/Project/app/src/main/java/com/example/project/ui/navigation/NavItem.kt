package com.example.project.ui.navigation


sealed class NavItem(var title: String, var icon: Int, var screenRoute: String) {
    object Home : NavItem(title = "홈", screenRoute = "HOME", icon = 0)
    object Login : NavItem(title = "로그인", screenRoute = "LOGIN", icon = 0)
}