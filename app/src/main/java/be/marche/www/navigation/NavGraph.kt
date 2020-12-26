
package be.marche.www.navigation

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import be.marche.www.navigation.Destinations.Agenda
import be.marche.www.navigation.Destinations.EventDetail
import be.marche.www.navigation.Destinations.News
import be.marche.www.navigation.Destinations.NewsDetail

object Destinations {
    const val Home = "home"
    const val News = "addProject"
    const val Agenda = "addTask"
    const val EventDetail = "editTask"
    const val NewsDetail = "taskDetail"

    object TaskDetailArgs {
        const val TaskId = "taskId"
    }
}

class Actions(navController: NavHostController) {
    val detailNews: (Int) -> Unit = { taskId ->
        navController.navigate("$EventDetail/$taskId")
    }
    val listNews: () -> Unit = {
        navController.navigate(News)
    }
    val detailEvent: (Int) -> Unit = { taskId ->
        navController.navigate("$NewsDetail/$taskId")
    }
    val listEvents: () -> Unit = {
        navController.navigate(Agenda)
    }
    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
}
