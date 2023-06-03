package com.example.nasasearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nasasearch.ui.theme.theme.NASASearchApp
import com.example.nasasearch.ui.theme.theme.theme.NASASearchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NASASearchTheme {
                NASASearchApp()
            }
        }
    }
}