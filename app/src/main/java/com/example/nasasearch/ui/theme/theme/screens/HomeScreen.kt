package com.example.nasasearch.ui.theme.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (nasaUiState) {
        is NASAUiState.Loading -> LoadingScreen(modifier = modifier)
        is NASAUiState.Success -> PhotosGridScreen(nasaUiState.photos, modifier = modifier)
        is NASAUiState.Error -> ErrorScreen(retryAction, modifier)

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
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Failed to load")
        Button(onClick = retryAction) {
            Text("Retry")
        }
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
    Card (
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),

        ){
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
}

@Composable
fun PhotosGridScreen(photos: Collection, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = photos.collection.items, key = {photos -> photos.data[0].nasaId}) {
            photo -> NASAPhotoCard(photo)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    NASASearchTheme {

    }
}
