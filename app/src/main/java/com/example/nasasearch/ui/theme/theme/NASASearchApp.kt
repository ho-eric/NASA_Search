package com.example.nasasearch.ui.theme.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.nasasearch.ui.theme.theme.screens.HomeScreen
import com.example.nasasearch.ui.theme.theme.screens.NASAViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NASASearchApp(modifier: Modifier = Modifier, navController: NavHostController, nasaViewModel: NASAViewModel) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(
            title = { Text("NASA Search App") }
        )

        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                nasaUiState = nasaViewModel.nasaUiState,
                retryAction = nasaViewModel::getNASAData,
                navController = navController
            )
        }
    }
}