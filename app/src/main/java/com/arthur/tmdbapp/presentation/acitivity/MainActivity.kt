package com.arthur.tmdbapp.presentation.acitivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arthur.tmdbapp.ui.navigation.NavArgs
import com.arthur.tmdbapp.ui.navigation.Routes
import com.arthur.tmdbapp.ui.screens.MovieDetailsScreen
import com.arthur.tmdbapp.ui.screens.MovieListScreen
import com.arthur.tmdbapp.ui.theme.TMDBAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDBAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavigation(navController = navController)
                }
            }
        }
    }
}


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.MOVIE_LIST
    ) {
        composable(route = Routes.MOVIE_LIST) {
            MovieListScreen(
                onMovieClick = { movieId ->
                    navController.navigate(Routes.movieDetails(movieId))
                }
            )
        }
        composable(
            route = Routes.MOVIE_DETAILS,
            arguments = listOf(navArgument(NavArgs.MOVIE_ID) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            MovieDetailsScreen(
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}