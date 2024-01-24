package com.takealot.composenav.composedestinations.screen

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.takealot.composenav.data.DemoDataProvider
import com.takealot.composenav.ui.components.ViewArticleContent

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ViewArticleScreen(
    articleId: String,
    navigator: DestinationsNavigator
) {
    val article = remember {
        DemoDataProvider.articleList.find { it.id == articleId }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ViewArticleScreen")
                },
                navigationIcon = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
            )
        },
    ) { paddingValues ->
        if (article != null) {
            ViewArticleContent(
                article = article,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            )
        } else {
            Text(text = "Article not found")
        }
    }
}