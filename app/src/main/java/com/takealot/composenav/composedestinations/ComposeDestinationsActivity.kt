package com.takealot.composenav.composedestinations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import com.takealot.composenav.composedestinations.screen.NavGraphs
import com.takealot.composenav.ui.theme.ComposeNavigationTheme

class ComposeDestinationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeNavigationTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}