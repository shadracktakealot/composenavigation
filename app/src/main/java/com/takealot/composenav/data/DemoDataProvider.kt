package com.takealot.composenav.data

import com.takealot.composenav.model.Article
import kotlinx.collections.immutable.toImmutableList

object DemoDataProvider {
    val article = Article(
        id = "1",
        title = "Compose Navigation Options",
        description = "Very awesome article"
    )

    val articleList = listOf(
        Article(
            id = "1",
            title = "Android Studio tips and tricks",
            description = "Very awesome article"
        ),
        Article(
            id = "2",
            title = "What's new in Kotlin 2.0?",
            description = "Very awesome article"
        ),
        Article(
            id = "3",
            title = "Compose Navigation Options",
            description = "Very awesome article"
        )
    ).toImmutableList()
}