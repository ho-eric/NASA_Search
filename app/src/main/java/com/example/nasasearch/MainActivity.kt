package com.example.nasasearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nasasearch.ui.theme.theme.NASASearchApp
import com.example.nasasearch.ui.theme.theme.screens.DetailsScreen
import com.example.nasasearch.ui.theme.theme.theme.NASASearchTheme
import com.example.nasasearch.ui.theme.theme.screens.NASAViewModel

class MainActivity : ComponentActivity() {

    private lateinit var nasaViewModel: NASAViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NASASearchTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home_screen") {

                    composable("home_screen") {
                        nasaViewModel = viewModel(factory = NASAViewModel.Factory)

                        NASASearchApp(navController = navController, nasaViewModel = nasaViewModel)
                    }
                    composable("details_screen") {

                        DetailsScreen(navController, nasaViewModel)
                    }
                }
            }
        }
    }
}
