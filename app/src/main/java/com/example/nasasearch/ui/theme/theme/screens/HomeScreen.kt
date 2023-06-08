package com.example.nasasearch.ui.theme.theme.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.nasasearch.R
import com.example.nasasearch.model.Collection
import com.example.nasasearch.model.Item
import com.example.nasasearch.ui.theme.theme.theme.NASASearchTheme

@Composable
fun HomeScreen(
    nasaUiState: NASAUiState,
    retryAction: (String) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val viewModel = viewModel<NASAViewModel>()

    when (nasaUiState) {
        is NASAUiState.Success -> PhotosGridScreen(
            nasaUiState.photos,
            modifier = modifier,
            viewModel = viewModel,
            navController = navController
        )

        is NASAUiState.Loading -> LoadingScreen(modifier = modifier)
        is NASAUiState.Error -> ErrorScreen({ viewModel.getNASAData("") }, modifier)
//        is NASAUiState.Details -> DetailsTestScreen(navController = navController)
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
fun ErrorScreen(retryAction: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Failed to load")
        Button(onClick = { retryAction("") }) {
            Text("Retry")
        }
    }
}

@Composable
fun NASAPhotoCard(
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
                navController.navigate("details_screen")
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
                contentDescription = "Test",
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
fun PhotosGridScreen(
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
                Text("Search")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
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
                        contentDescription = "Close icon"
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, viewModel: NASAViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            TopAppBar(
                title = { Text("NASA Search App") },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { navController.popBackStack() },
                        imageVector = Icons.Default.ArrowBack, contentDescription = "Back arrow"
                    )
                }
            )

        }
    ) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {

            Column {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(viewModel.nasaImage)
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = "Test"
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = viewModel.nasaTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,

                    )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = viewModel.nasaCreationDate
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = viewModel.nasaDescription
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
