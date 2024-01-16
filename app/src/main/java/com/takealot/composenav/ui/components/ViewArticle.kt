package com.takealot.composenav.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.takealot.composenav.model.Article

@Composable
fun ViewArticleContent(
    article: Article,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = article.title)
        Text(text = article.description)
    }
}