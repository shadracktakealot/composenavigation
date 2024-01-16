package com.takealot.composenav.decompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.defaultComponentContext
import com.takealot.composenav.decompose.navigation.DefaultRootComponent
import com.takealot.composenav.decompose.navigation.RootScreen
import com.takealot.composenav.ui.theme.ComposeNavigationTheme

class DecomposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = DefaultRootComponent(
            componentContext = defaultComponentContext()
        )

        setContent {
            ComposeNavigationTheme {
                var currentScreenTitle by remember {
                    mutableStateOf("")
                }

                RootScreen(
                    component = rootComponent,
                    currentScreenTitle = currentScreenTitle,
                    onUpdateScreenTitle = {
                        currentScreenTitle = it
                    }
                )
            }
        }
    }
}