package com.takealot.composenav.navcomponent.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.takealot.composenav.navcomponent.screen.ArticleListScreen
import com.takealot.composenav.navcomponent.screen.ViewArticleScreen

sealed class NavigationItem(val route: String) {
    object ArticleList : NavigationItem(route = "article_list")
    object ViewArticle : NavigationItem(route = "view_article")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.ArticleList.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = NavigationItem.ArticleList.route) {
            ArticleListScreen(navController = navController)
        }
        composable(
            route = "${NavigationItem.ViewArticle.route}/{articleId}",
            arguments = listOf(navArgument(name = "articleId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId")
            if (articleId != null) {
                ViewArticleScreen(articleId = articleId, navController = navController )
            }
        }
    }
}