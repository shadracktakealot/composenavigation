package com.takealot.composenav.voyager.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.takealot.composenav.data.DemoDataProvider
import com.takealot.composenav.ui.components.ViewArticleContent

data class ViewArticleScreen(val articleId: String) : Screen {
    @Composable
    override fun Content() {
        val article = remember {
            DemoDataProvider.articleList.find { it.id == articleId }
        }

        if (article != null) {
            ViewArticleContent(
                article = article,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Text(text = "Article not found")
        }
    }
}