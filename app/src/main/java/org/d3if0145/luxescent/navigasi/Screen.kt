package org.d3if0145.luxescent.navigasi

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("AboutScreen")
}