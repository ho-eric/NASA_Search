package com.example.nasasearch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nasasearch.R

const val LOADING_SCREEN_TEST_TAG = "loadingscreentag"
private const val LOADING_INDICATOR_CONTENT_DESCRIPTION = "Loading Indicator"

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize().testTag(LOADING_SCREEN_TEST_TAG)
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = LOADING_INDICATOR_CONTENT_DESCRIPTION
        )
    }
}