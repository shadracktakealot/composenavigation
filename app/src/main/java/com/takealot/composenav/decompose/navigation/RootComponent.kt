package com.takealot.composenav.decompose.navigation

import android.os.Parcelable
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.takealot.composenav.decompose.screen.ArticleListComponent
import com.takealot.composenav.decompose.screen.ArticleListScreen
import com.takealot.composenav.decompose.screen.DefaultArticleListComponent
import com.takealot.composenav.decompose.screen.DefaultViewArticleComponent
import com.takealot.composenav.decompose.screen.ViewArticleComponent
import com.takealot.composenav.decompose.screen.ViewArticleScreen
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    component: RootComponent,
    currentScreenTitle: String,
    onUpdateScreenTitle: (title: String) -> Unit
) {
    Children(
        stack = component.stack,
        modifier = Modifier.fillMaxSize(),
        animation = stackAnimation(fade())
    ) { currentChild ->

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = currentScreenTitle) },
                    navigationIcon = {
                        val child = currentChild.instance
                        if (child is RootComponent.Child.ViewArticleChild) {
                            IconButton(onClick = { child.component.onBackPressed() }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            when (val child = currentChild.instance) {
                is RootComponent.Child.ArticleListChild -> {
                    onUpdateScreenTitle("ArticleListScreen")
                    ArticleListScreen(
                        component = child.component,
                        modifier = Modifier.padding(paddingValues)
                    )
                }

                is RootComponent.Child.ViewArticleChild -> {
                    onUpdateScreenTitle("ViewArticleScreen")
                    ViewArticleScreen(
                        component = child.component,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class ArticleListChild(
            val component: ArticleListComponent
        ) : Child()

        class ViewArticleChild(
            val component: ViewArticleComponent
        ) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.ArticleList,
        handleBackButton = true,
        childFactory = ::childFactory
    )

    private fun childFactory(
        config: Config, componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.ArticleList -> RootComponent.Child.ArticleListChild(
                component = articleListComponent(componentContext)
            )

            is Config.ViewArticle -> RootComponent.Child.ViewArticleChild(
                component = viewArticleComponent(
                    componentContext = componentContext, config = config
                )
            )
        }
    }

    private fun articleListComponent(componentContext: ComponentContext): ArticleListComponent =
        DefaultArticleListComponent(componentContext = componentContext,
            onArticleClicked = { article ->
                navigation.push(Config.ViewArticle(articleId = article.id))
            })

    private fun viewArticleComponent(
        componentContext: ComponentContext, config: Config.ViewArticle
    ): ViewArticleComponent = DefaultViewArticleComponent(
        componentContext = componentContext,
        articleId = config.articleId,
        onBackPressed = navigation::pop
    )

    @Parcelize
    private sealed class Config : Parcelable {
        object ArticleList : Config()
        data class ViewArticle(val articleId: String) : Config()
    }
}