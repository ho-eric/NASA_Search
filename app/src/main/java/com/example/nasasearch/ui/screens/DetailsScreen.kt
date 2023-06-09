package com.example.nasasearch.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.nasasearch.R
import com.example.nasasearch.ui.NASAViewModel

const val BACK_BUTTON_TEST_TAG = "backbuttontag"
const val DETAILS_SCREEN_TEST_TAG = "detailsscreentag"
const val DETAILS_SCREEN_IMAGE_TAG = "detailsimagetag"
const val DETAILS_SCREEN_TITLE_TAG = "detailstitletag"
const val DETAILS_SCREEN_DATE_TAG = "detailsdatetag"
const val DETAILS_SCREEN_DESCRIPTION_TAG = "detailsdescriptiontag"

private const val BACK_ARROW_CONTENT_DESCRIPTION = "Back arrow"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, viewModel: NASAViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize().testTag(DETAILS_SCREEN_TEST_TAG),
        topBar = {

            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable { navController.popBackStack() }.testTag(
                            BACK_BUTTON_TEST_TAG),
                        imageVector = Icons.Default.ArrowBack, contentDescription = BACK_ARROW_CONTENT_DESCRIPTION
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Column {
                AsyncImage(
                    modifier = Modifier.fillMaxSize().testTag(DETAILS_SCREEN_IMAGE_TAG),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(viewModel.nasaImage)
                        .crossfade(true)
                        .build(),
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = viewModel.nasaTitle
                )
                Text(
                    modifier = Modifier.padding(5.dp).testTag(DETAILS_SCREEN_TITLE_TAG),
                    text = viewModel.nasaTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,

                    )
                Text(
                    modifier = Modifier.padding(5.dp).testTag(DETAILS_SCREEN_DATE_TAG),
                    text = viewModel.nasaCreationDate
                )
                Text(
                    modifier = Modifier.padding(5.dp).testTag(DETAILS_SCREEN_DESCRIPTION_TAG),
                    text = viewModel.nasaDescription
                )
            }
        }
    }
}