package com.example.nasasearch.ui.theme.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nasasearch.ui.theme.theme.theme.NASASearchTheme

@Composable
fun HomeScreen(
    nasaUiState: String,
    modifier: Modifier = Modifier
) {
    ResultScreen(nasaUiState, modifier)
}

@Composable
fun ResultScreen(nasaUiState: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(nasaUiState)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    NASASearchTheme {
        ResultScreen("TEST")
    }
}
