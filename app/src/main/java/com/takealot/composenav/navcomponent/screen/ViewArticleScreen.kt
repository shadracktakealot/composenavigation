package com.takealot.composenav.navcomponent.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.takealot.composenav.data.DemoDataProvider
import com.takealot.composenav.ui.components.ViewArticleContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewArticleScreen(
    articleId: String,
    navController: NavController,
) {
    val article = remember {
        DemoDataProvider.articleList.find { it.id == articleId }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "View Article Screen: id=$articleId")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
            )
        },
    ) { paddingValues ->
        if (article != null) {
            ViewArticleContent(
                article = article,
                modifier = Modifier.padding(paddingValues)
            )
        } else {
            Text(text = "Article not found")
        }
    }
}