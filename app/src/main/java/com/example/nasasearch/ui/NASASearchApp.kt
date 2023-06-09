package com.example.nasasearch.ui

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.nasasearch.R
import com.example.nasasearch.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NASASearchApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    nasaViewModel: NASAViewModel
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
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
                navController = navController
            )
        }
    }
}