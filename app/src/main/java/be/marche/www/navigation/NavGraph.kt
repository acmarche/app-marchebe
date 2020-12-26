package be.marche.www.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import be.marche.www.navigation.Destinations.Agenda
import be.marche.www.navigation.Destinations.EventShow
import be.marche.www.navigation.Destinations.News
import be.marche.www.navigation.Destinations.NewsShow
import be.marche.www.navigation.Destinations.TaskDetailArgs.eventId
import be.marche.www.navigation.Destinations.TaskDetailArgs.newsId

object Destinations {
    const val Home = "home"
    const val News = "listNews"
    const val Agenda = "listEvents"
    const val EventShow = "eventShow"
    const val NewsShow = "newsShow"

    object TaskDetailArgs {
        const val eventId = "eventId"
        const val newsId = "newsId"
    }
}

class Actions(navController: NavHostController) {
    val listNews: () -> Unit = {
        navController.navigate(News)
    }
    val newsShow: (Int) -> Unit = { taskId ->
        navController.navigate("$NewsShow/$newsId")
    }
    val listEvents: () -> Unit = {
        navController.navigate(Agenda)
    }
    val eventShow: (Int) -> Unit = { taskId ->
        navController.navigate("$EventShow/$eventId")
    }
    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
}
