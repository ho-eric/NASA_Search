package com.example.nasasearch.ui.theme.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.nasasearch.R

//@Composable
//fun DetailsScreen(viewModel: NASAViewModel) {
//    Column(Modifier.verticalScroll(rememberScrollState())) {
//        Icon(
//            modifier = Modifier.clickable {},
//            imageVector = Icons.Default.ArrowBack, contentDescription = "Back arrow"
//        )
//        Column {
//            AsyncImage(
//                modifier = Modifier.fillMaxSize(),
//                model = ImageRequest.Builder(context = LocalContext.current)
//                    .data(viewModel.nasaImage)
//                    .crossfade(true)
//                    .build(),
//                error = painterResource(R.drawable.ic_broken_image),
//                placeholder = painterResource(R.drawable.loading_img),
//                contentDescription = "Test"
//            )
//            Text(text = viewModel.nasaTitle)
//            Text(text = viewModel.nasaDescription)
//            Text(text = viewModel.nasaCreationDate)
//        }
//    }
//}