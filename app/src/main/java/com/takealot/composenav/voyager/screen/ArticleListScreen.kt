package com.takealot.composenav.voyager.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.takealot.composenav.data.DemoDataProvider
import com.takealot.composenav.ui.components.ArticleListContent

object ArticleListScreen : Screen {
    @Composable
    override fun Content() {
        val articles = remember {
            DemoDataProvider.articleList
        }
        val navigator = LocalNavigator.currentOrThrow

        ArticleListContent(
            articles = articles,
            onArticleClick = {
                navigator.push(item = ViewArticleScreen(articleId = it.id))
            }
        )
    }
}