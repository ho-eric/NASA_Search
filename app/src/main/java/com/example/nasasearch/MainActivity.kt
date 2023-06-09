package com.example.nasasearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nasasearch.ui.NASASearchApp
import com.example.nasasearch.ui.screens.DetailsScreen
import com.example.nasasearch.theme.NASASearchTheme
import com.example.nasasearch.ui.NASAViewModel

const val ROUTE_HOME_SCREEN = "home_screen"
const val ROUTE_DETAILS_SCREEN = "details_screen"


class MainActivity : ComponentActivity() {
    private lateinit var nasaViewModel: NASAViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NASASearchTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ROUTE_HOME_SCREEN) {
                    composable(ROUTE_HOME_SCREEN) {
                        nasaViewModel = viewModel(factory = NASAViewModel.Factory)
                        NASASearchApp(navController = navController, nasaViewModel = nasaViewModel)
                    }
                    composable(ROUTE_DETAILS_SCREEN) {
                        DetailsScreen(navController, nasaViewModel)
                    }
                }
            }
        }
    }
}
