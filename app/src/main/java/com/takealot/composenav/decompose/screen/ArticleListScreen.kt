package com.takealot.composenav.decompose.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.takealot.composenav.data.DemoDataProvider
import com.takealot.composenav.model.Article
import com.takealot.composenav.ui.components.ArticleListContent
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ArticleListScreen(
    component: ArticleListComponent,
    modifier: Modifier = Modifier
) {
    val state by component.state.subscribeAsState()

    ArticleListContent(
        articles = state.articles,
        modifier = modifier,
        onArticleClick = component::onArticleClicked,
    )
}

interface ArticleListComponent {
    val state: Value<State>

    fun onArticleClicked(article: Article)

    data class State(val articles: ImmutableList<Article>)
}

class DefaultArticleListComponent(
    componentContext: ComponentContext,
    private val onArticleClicked: (article: Article) -> Unit
) : ArticleListComponent, ComponentContext by componentContext {
    override val state: Value<ArticleListComponent.State> = MutableValue(
        ArticleListComponent.State(articles = DemoDataProvider.articleList)
    )

    override fun onArticleClicked(article: Article) = onArticleClicked.invoke(article)
}