package com.takealot.composenav.composedestinations.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.takealot.composenav.composedestinations.screen.destinations.ViewArticleScreenDestination
import com.takealot.composenav.data.DemoDataProvider
import com.takealot.composenav.ui.components.ArticleListContent

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun ArticleListScreen(
    navigator: DestinationsNavigator
) {
    val articles = remember {
        DemoDataProvider.articleList
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ArticleListScreen")
                }
            )
        }
    ) { paddingValues ->
        ArticleListContent(
            articles = articles,
            modifier = Modifier.padding(paddingValues),
            onArticleClick = {
                navigator.navigate(ViewArticleScreenDestination(articleId = it.id))
            }
        )
    }
}