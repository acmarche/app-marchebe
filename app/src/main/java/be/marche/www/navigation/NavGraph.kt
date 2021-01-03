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

    NavHost(navController = navController, startDestination = Router.Routes.Home) {
        composable(Router.Routes.Home) {
            MainScreen(
                listNews = navigateTo.listNews,
                listEvents = navigateTo.listEvents,
                listFiches = navigateTo.listFiches,
                navigateUp = navigateTo.navigateUp
            )
        }
        composable(Router.Routes.News) {
            ListNewsScreen(
                newsViewModel.findAllNews(),
                onItemClick = navigateTo.newsShow,
                navigateUp = navigateTo.navigateUp
            )
        }
        composable(
            route = Router.Routes.NewsShow,
            arguments = listOf(navArgument(Router.Routes.routeArgs.newsId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            NewsShowComponent(
                newsId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.newsId) ?: 0,
                newsViewModel,
                navigateUp = navigateTo.listNews
            )
        }
        composable(Router.Routes.Agenda) {
            ListEventsComponent(
                eventViewModel.findAllEvent(),
                onItemClick = navigateTo.eventShow,
                navigateUp = navigateTo.navigateUp
            )
        }
        composable(
            route = Router.Routes.EventShow,
            arguments = listOf(navArgument(Router.Routes.routeArgs.eventId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            EventShowScreen(
                eventId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.eventId) ?: 0,
                eventViewModel = eventViewModel,
                navigateUp = navigateTo.listEvents
            )
        }
        composable(
            route = Router.Routes.Fiches,
            arguments = listOf(navArgument(Router.Routes.routeArgs.categoryId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            ListFichesScreen(
                categoryId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.categoryId) ?: 0,
                categoryViewModel = categoryViewModel,
                ficheViewModel = ficheViewModel,
                onCategoryClick = navigateTo.listFiches,
                onFicheItemClick = navigateTo.ficheShow,
                navigateUp = navigateTo.navigateUp
            )
        }
        composable(
            route = Router.Routes.FicheShow,
            arguments = listOf(
                navArgument(Router.Routes.routeArgs.categoryId) {
                    type = NavType.IntType
                },
                navArgument(Router.Routes.routeArgs.ficheId) {
                    type = NavType.IntType
                })
        ) { backStackEntry ->
            val ficheUi = FicheUi()
            ficheUi.ShowScreen(
                categoryId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.categoryId) ?: 0,
                ficheId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.ficheId) ?: 0,
                categoryViewModel = categoryViewModel,
                ficheViewModel = ficheViewModel,
                navigateUp = navigateTo.navigateUp
            )
        }
    }
}