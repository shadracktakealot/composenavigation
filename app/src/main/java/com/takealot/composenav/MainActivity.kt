package com.takealot.composenav

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.takealot.composenav.decompose.DecomposeActivity
import com.takealot.composenav.navigationcomponent.NavigationComposeActivity
import com.takealot.composenav.ui.theme.ComposeNavigationTheme
import com.takealot.composenav.voyager.VoyagerActivity
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Column(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally) {
                       Button(onClick = { startActivity(getIntent(DecomposeActivity::class)) }) {
                           Text(text = "Decompose")
                       }
                       Button(onClick = { startActivity(getIntent(NavigationComposeActivity::class)) }) {
                           Text(text = "Navigation Compose")
                       }
                       Button(onClick = { startActivity(getIntent(VoyagerActivity::class)) }) {
                           Text(text = "Voyager")
                       }
                   }
                }
            }
        }
    }

    private fun getIntent(activity: KClass<*>): Intent {
        return Intent(this, activity.java)
    }
}