package be.marche.www.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.bottin.ui.FicheListScreen
import be.marche.www.bottin.ui.FicheScreen
import be.marche.www.event.EventViewModel
import be.marche.www.event.ui.EventListScreen
import be.marche.www.event.ui.EventScreen
import be.marche.www.home.HomeScreen
import be.marche.www.news.NewsViewModel
import be.marche.www.news.ui.NewsListScreen
import be.marche.www.news.ui.NewsScreen
import be.marche.www.ui.pages.UrgenceScreen

@Composable
fun RegisterRoutes(
    newsViewModel: NewsViewModel,
    eventViewModel: EventViewModel,
    ficheViewModel: FicheViewModel,
    categoryViewModel: CategoryViewModel
) {
    val navController = rememberNavController()
    val navigateTo = remember(navController) { Actions(navController) }

    NavHost(navController = navController, startDestination = Router.Routes.Home) {
        composable(Router.Routes.Home) {
            val screen = HomeScreen()
            screen.HomeComponent(
                navigateTo = navigateTo
            )
        }
        composable(Router.Routes.News) {
            val screen = NewsListScreen()
            screen.ListComponent(
                newsViewModel.findAllNews(),
                navigateTo = navigateTo,
            )
        }
        composable(
            route = Router.Routes.NewsShow,
            arguments = listOf(navArgument(Router.Routes.routeArgs.newsId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val screen = NewsScreen()
            screen.ShowComponent(
                newsId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.newsId) ?: 0,
                newsViewModel,
                navigateTo = navigateTo,
            )
        }
        composable(Router.Routes.Agenda) {
            val screen = EventListScreen()
            screen.ListComponent(
                eventViewModel.findAllEvent(),
                navigateTo = navigateTo,
            )
        }
        composable(
            route = Router.Routes.EventShow,
            arguments = listOf(navArgument(Router.Routes.routeArgs.eventId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val screen = EventScreen()
            screen.ShowComponent(
                eventId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.eventId) ?: 0,
                eventViewModel = eventViewModel,
                navigateTo = navigateTo,
            )
        }
        composable(
            route = Router.Routes.Fiches,
            arguments = listOf(navArgument(Router.Routes.routeArgs.categoryId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val screen = FicheListScreen()
            screen.NavigateCategoryComponent(
                categoryId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.categoryId)
                    ?: 0,
                categoryViewModel = categoryViewModel,
                ficheViewModel = ficheViewModel,
                navigateTo = navigateTo,
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
            val screen = FicheScreen()
            screen.ShowComponent(
                categoryId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.categoryId)
                    ?: 0,
                ficheId = backStackEntry.arguments?.getInt(Router.Routes.routeArgs.ficheId) ?: 0,
                categoryViewModel = categoryViewModel,
                ficheViewModel = ficheViewModel,
                navigateTo = navigateTo,
            )
        }
        composable(Router.Routes.Urgence) {
            val screen = UrgenceScreen()
            screen.ListComponent(
                navigateTo = navigateTo,
            )
        }
    }
}