package com.example.nasasearch.ui.theme.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nasasearch.R
import com.example.nasasearch.ui.theme.theme.theme.NASASearchTheme

@Composable
fun HomeScreen(
    nasaUiState: NASAUiState,
    modifier: Modifier = Modifier
) {
    when (nasaUiState) {
        is NASAUiState.Loading -> LoadingScreen(modifier)
        is NASAUiState.Success -> ResultScreen(nasaUiState.photos, modifier)
        else -> ErrorScreen(modifier)

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = "Loading"
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Failed to load")
    }

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
