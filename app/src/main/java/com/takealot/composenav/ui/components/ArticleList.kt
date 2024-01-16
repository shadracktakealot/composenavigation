package com.takealot.composenav.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.takealot.composenav.model.Article
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ArticleListContent(
    articles: ImmutableList<Article>,
    modifier: Modifier = Modifier,
    onArticleClick: (article: Article) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(articles) { article ->
            ArticleListItem(
                article = article,
                onArticleClick = onArticleClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListItem(
    article: Article,
    onArticleClick: (article: Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onArticleClick(article) }
    ) {
        ListItem(
            modifier = Modifier.padding(8.dp),
            headlineText = { Text(text = article.title) }
        )
        Divider(Modifier.height(1.dp))
    }
}