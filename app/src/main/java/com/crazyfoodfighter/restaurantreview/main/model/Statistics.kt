package com.crazyfoodfighter.restaurantreview.main.model

data class Statistics(
    val totalRestaurants: Int,
    val favoriteCategory: String,
    val favoriteMenu: String,
    val reviewsThisMonth: Int
)