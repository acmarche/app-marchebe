package be.marche.www.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

/**
 *
 */
class Actions(navController: NavHostController) {
    val listNews: () -> Unit = {
        navController.navigate(Routes.News)
    }
    val newsShow: (newsId: Int) -> Unit = { newsId ->
        navController.navigate("newsShow/$newsId")
    }
    val listEvents: () -> Unit = {
        navController.navigate(Routes.Agenda)
    }
    val eventShow: (eventId: Int) -> Unit = { eventId ->
        navController.navigate("eventShow/$eventId")
    }
    val listFiches: (categoryId: Int) -> Unit = { categoryId ->
        navController.navigate("listFiches/$categoryId")
    }
    val ficheShow: (categoryId: Int, ficheId: Int) -> Unit = { categoryId, ficheId ->
        navController.navigate("ficheShow/$categoryId/$ficheId")
    }
    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
}