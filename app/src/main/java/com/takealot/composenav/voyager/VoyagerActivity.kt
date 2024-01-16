package com.takealot.composenav.voyager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.takealot.composenav.ui.theme.ComposeNavigationTheme
import com.takealot.composenav.voyager.screen.ArticleListScreen

class VoyagerActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeNavigationTheme {
                Navigator(
                    screen = ArticleListScreen,
                    onBackPressed = { currentScreen ->
                        println("onBackPressed: screenKey = ${currentScreen.key}")
                        true
                    }
                ) { navigator ->
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = navigator.lastItem.key.split(".").last()
                                    )
                                },
                                navigationIcon = {
                                    if (navigator.canPop) {
                                        IconButton(onClick = { navigator.pop() }) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowBack,
                                                contentDescription = ""
                                            )
                                        }
                                    }
                                },
                            )
                        },
                    ) { paddingValues ->
                        Surface(modifier = Modifier.padding(paddingValues)) {
                            CurrentScreen()
                        }
                    }
                }
            }
        }
    }
}