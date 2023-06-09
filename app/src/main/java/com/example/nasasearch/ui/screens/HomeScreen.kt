package com.example.nasasearch.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.nasasearch.R
import com.example.nasasearch.ROUTE_DETAILS_SCREEN
import com.example.nasasearch.model.Collection
import com.example.nasasearch.model.Item
import com.example.nasasearch.theme.NASASearchTheme
import com.example.nasasearch.ui.NASAUiState
import com.example.nasasearch.ui.NASAViewModel

private const val PHOTO_CARD_CONTENT_DESCRIPTION = "A NASA photo card"
private const val SEARCH_ICON = "Search icon"
private const val SEARCH_TEXT = "Search"
private const val CLOSE_ICON = "Close icon"
@Composable
fun HomeScreen(
    nasaUiState: NASAUiState,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val viewModel = viewModel<NASAViewModel>()

    when (nasaUiState) {
        is NASAUiState.Success -> PhotosGridScreen(
            photos = nasaUiState.photos,
            modifier = modifier,
            viewModel = viewModel,
            navController = navController
        )
        is NASAUiState.Loading -> LoadingScreen(modifier = modifier)
        is NASAUiState.Error -> ErrorScreen({ viewModel.getNASAData("") }, modifier)
    }
}

@Composable
private fun NASAPhotoCard(
    photo: Item,
    modifier: Modifier = Modifier,
    viewModel: NASAViewModel,
    navController: NavController
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.setDetailsScreen(
                    photo.links[0].href,
                    photo.data[0].title,
                    photo.data[0].description,
                    photo.data[0].dateCreated
                )
                navController.navigate(ROUTE_DETAILS_SCREEN)
            }
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),

        ) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(photo.links[0].href)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = PHOTO_CARD_CONTENT_DESCRIPTION,
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 100f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = photo.data[0].title,
                    style = TextStyle(color = Color.White, fontSize = 15.sp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotosGridScreen(
    photos: Collection,
    modifier: Modifier = Modifier,
    viewModel: NASAViewModel,
    navController: NavController
) {
    var searchTerm by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = searchTerm,
            onQueryChange = {
                searchTerm = it
            },
            onSearch = {
                viewModel.getNASAData(searchTerm)
                active = false
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(SEARCH_TEXT)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = SEARCH_ICON)
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (searchTerm.isNotEmpty()) {
                                searchTerm = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = CLOSE_ICON
                    )
                }
            }
        ) {
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(
                items = photos.collection.items,
                key = { photos -> photos.data[0].nasaId }) { photo ->
                NASAPhotoCard(
                    viewModel = viewModel, photo = photo, navController = navController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    NASASearchTheme {
    }
}
