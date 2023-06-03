package com.example.nasasearch.ui.theme.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.nasasearch.R
import com.example.nasasearch.model.Collection
import com.example.nasasearch.model.Item
import com.example.nasasearch.ui.theme.theme.theme.NASASearchTheme

@Composable
fun HomeScreen(
    nasaUiState: NASAUiState,
    modifier: Modifier = Modifier
) {
    when (nasaUiState) {
        is NASAUiState.Loading -> LoadingScreen(modifier = modifier)
        is NASAUiState.Success -> NASAPhotoCard(photo = nasaUiState.photos, modifier = modifier)
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

@Composable
fun NASAPhotoCard(photo: Item, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(photo.links[0].href)
            .crossfade(true)
            .build(),
        error = painterResource(R.drawable.ic_broken_image),
        placeholder = painterResource(R.drawable.loading_img),
        contentDescription = "Test",
        contentScale = ContentScale.FillBounds
    )

}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    NASASearchTheme {
        ResultScreen("TEST")
    }
}
