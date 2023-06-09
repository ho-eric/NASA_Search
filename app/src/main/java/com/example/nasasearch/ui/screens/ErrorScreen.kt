package com.example.nasasearch.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private const val LOADING_FAILED_TEXT = "Failed to load"
private const val RETRY_TEXT = "Retry"

@Composable
fun ErrorScreen(retryAction: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(LOADING_FAILED_TEXT)
        Button(onClick = { retryAction("") }) {
            Text(RETRY_TEXT)
        }
    }
}