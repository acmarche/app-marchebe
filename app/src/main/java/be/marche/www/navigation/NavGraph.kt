package be.marche.www.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import be.marche.www.event.EventViewModel
import be.marche.www.event.ui.ListEventsComponent
import be.marche.www.news.NewsViewModel
import be.marche.www.news.ui.ListNewsComponent
import be.marche.www.news.ui.NewsShowComponent
import be.marche.www.ui.HomeComponent

object Routes {
    const val Home = "home"
    const val News = "listNews"
    const val Agenda = "listEvents"
    const val EventShow = "eventShow/{eventId}"
    const val NewsShow = "newsShow/{newsId}"
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
            HomeComponent(
                listNews = navigateTo.listNews,
                listEvents = navigateTo.listEvents
            )
        }
        composable(Routes.News) {
            ListNewsComponent(
                newsViewModel.allNews,
                onItemClick = navigateTo.newsShow
            )
        }
        composable(
            route = Routes.NewsShow,
            arguments = listOf(navArgument("newsId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            NewsShowComponent(
                newsId = backStackEntry.arguments?.getInt("newsId") ?: 0,
                //navigateUp = actions.navigateUp
            )
        }
        composable(Routes.EventShow) {
            NewsShowComponent(
                2
            )
        }
        composable(Routes.Agenda) {
            ListEventsComponent(
                eventViewModel.allEvents,
                onItemClick = navigateTo.eventShow
            )
        }
    }
}