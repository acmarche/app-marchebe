package be.marche.www.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import be.marche.www.event.EventViewModel
import be.marche.www.event.ui.EventShowScreen
import be.marche.www.event.ui.ListEventsComponent
import be.marche.www.home.MainScreen
import be.marche.www.news.NewsViewModel
import be.marche.www.news.ui.ListNewsScreen
import be.marche.www.news.ui.NewsShowComponent

object Routes {
    const val Home = "home"
    const val News = "listNews"
    const val Agenda = "listEvents"
    const val EventShow = "eventShow/{eventId}"///{eventId}
    const val NewsShow = "newsShow/{newsId}"

    object routeArgs {
        const val eventId = "eventId"
        const val newsId = "newsId"
    }
}

class Actions(navController: NavHostController) {
    val listNews: () -> Unit = {
        navController.navigate(Routes.News)
    }
    val newsShow: (Int) -> Unit = { newsId ->
        navController.navigate("newsShow/$newsId")
    }
    val listEvents: () -> Unit = {
        navController.navigate(Routes.Agenda)
    }
    val eventShow: (Int) -> Unit = { eventId ->
        navController.navigate("eventShow/$eventId")
    }
    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
}

@Composable
fun RegisterRoutes(newsViewModel: NewsViewModel, eventViewModel: EventViewModel) {
    val navController = rememberNavController()
    val navigateTo = remember(navController) { Actions(navController) }

    NavHost(navController = navController, startDestination = Routes.Home) {
        composable(Routes.Home) {
            MainScreen(
                listNews = navigateTo.listNews,
                listEvents = navigateTo.listEvents,
                navigateUp = navigateTo.navigateUp
            )
        }
        composable(Routes.News) {
            ListNewsScreen(
                newsViewModel.findAllNews(),
                onItemClick = navigateTo.newsShow
            )
        }
        composable(
            route = Routes.NewsShow,
            arguments = listOf(navArgument(Routes.routeArgs.newsId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            NewsShowComponent(
                newsId = backStackEntry.arguments?.getInt(Routes.routeArgs.newsId) ?: 0,
                //navigateUp = actions.navigateUp
            )
        }
        composable(Routes.Agenda) {
            ListEventsComponent(
                eventViewModel.findAllEvent(),
                onItemClick = navigateTo.eventShow
            )
        }
        composable(
            route = Routes.EventShow,
            arguments = listOf(navArgument(Routes.routeArgs.eventId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            EventShowScreen(
                eventId = backStackEntry.arguments?.getInt(Routes.routeArgs.eventId) ?: 0,
                //navigateUp = actions.navigateUp
            )
        }
    }
}