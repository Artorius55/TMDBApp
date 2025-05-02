package com.arthur.tmdbapp.ui.navigation

object Routes {
    const val MOVIE_LIST = "movie_list_screen"
    const val MOVIE_DETAILS = "movie_details_screen/{movieId}"

    fun movieDetails(movieId: Int) = "movie_details_screen/$movieId"
}

object NavArgs {
    const val MOVIE_ID = "movieId"
}