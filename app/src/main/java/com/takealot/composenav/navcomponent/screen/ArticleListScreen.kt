package com.takealot.composenav.navcomponent.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.takealot.composenav.data.DemoDataProvider
import com.takealot.composenav.navcomponent.navigation.NavigationItem
import com.takealot.composenav.ui.components.ArticleListContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen(navController: NavController) {
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
                navController.navigate("${NavigationItem.ViewArticle.route}/${it.id}")
            }
        )
    }
}