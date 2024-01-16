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
import com.takealot.composenav.ui.components.ViewArticleContent

@Composable
fun ViewArticleScreen(
    component: ViewArticleComponent,
    modifier: Modifier = Modifier
) {
    val state by component.state.subscribeAsState()
    
    ViewArticleContent(
        article = state.article,
        modifier = modifier
    )
}

interface ViewArticleComponent {
    val state: Value<State>

    fun onBackPressed()

    data class State(val article: Article)
}

class DefaultViewArticleComponent(
    componentContext: ComponentContext,
    private val articleId: String,
    private val onBackPressed: () -> Unit
) : ViewArticleComponent, ComponentContext by componentContext {
    override val state: Value<ViewArticleComponent.State> = MutableValue(
        ViewArticleComponent.State(
            article = DemoDataProvider.articleList.find { it.id == articleId }!!
        )
    )

    override fun onBackPressed() = onBackPressed.invoke()
}