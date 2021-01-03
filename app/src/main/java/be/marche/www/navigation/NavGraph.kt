package be.marche.www.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.ClassementViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.bottin.ui.FicheUi
import be.marche.www.bottin.ui.ListFichesScreen
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
    const val EventShow = "eventShow/{eventId}"
    const val NewsShow = "newsShow/{newsId}"
    const val Fiches = "listFiches/{categoryId}"
    const val FicheShow = "ficheShow/{categoryId}/{ficheId}"

    object routeArgs {
        const val eventId = "eventId"
        const val newsId = "newsId"
        const val ficheId = "ficheId"
        const val categoryId = "categoryId"
    }
}


@Composable
fun RegisterRoutes(
    newsViewModel: NewsViewModel,
    eventViewModel: EventViewModel,
    ficheViewModel: FicheViewModel,
    categoryViewModel: CategoryViewModel,
    classementViewModel: ClassementViewModel
) {
    val navController = rememberNavController()
    val navigateTo = remember(navController) { Actions(navController) }

    NavHost(navController = navController, startDestination = Routes.Home) {
        composable(Routes.Home) {
            MainScreen(
                listNews = navigateTo.listNews,
                listEvents = navigateTo.listEvents,
                listFiches = navigateTo.listFiches,
                navigateUp = navigateTo.navigateUp
            )
        }
        composable(Routes.News) {
            ListNewsScreen(
                newsViewModel.findAllNews(),
                onItemClick = navigateTo.newsShow,
                navigateUp = navigateTo.navigateUp
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
                newsViewModel,
                navigateUp = navigateTo.listNews
            )
        }
        composable(Routes.Agenda) {
            ListEventsComponent(
                eventViewModel.findAllEvent(),
                onItemClick = navigateTo.eventShow,
                navigateUp = navigateTo.navigateUp
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
                eventViewModel = eventViewModel,
                navigateUp = navigateTo.listEvents
            )
        }
        composable(
            route = Routes.Fiches,
            arguments = listOf(navArgument(Routes.routeArgs.categoryId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            ListFichesScreen(
                categoryId = backStackEntry.arguments?.getInt(Routes.routeArgs.categoryId) ?: 0,
                categoryViewModel = categoryViewModel,
                ficheViewModel = ficheViewModel,
                onCategoryClick = navigateTo.listFiches,
                onFicheItemClick = navigateTo.ficheShow,
                navigateUp = navigateTo.navigateUp
            )
        }
        composable(
            route = Routes.FicheShow,
            arguments = listOf(
                navArgument(Routes.routeArgs.categoryId) {
                    type = NavType.IntType
                },
                navArgument(Routes.routeArgs.ficheId) {
                    type = NavType.IntType
                })
        ) { backStackEntry ->
            val ficheUi = FicheUi()
            ficheUi.ShowScreen(
                categoryId = backStackEntry.arguments?.getInt(Routes.routeArgs.categoryId) ?: 0,
                ficheId = backStackEntry.arguments?.getInt(Routes.routeArgs.ficheId) ?: 0,
                categoryViewModel = categoryViewModel,
                ficheViewModel = ficheViewModel,
                navigateUp = navigateTo.navigateUp
            )
        }
    }
}