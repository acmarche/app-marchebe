package be.marche.www.navigation

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import be.marche.www.utils.makeOpenUrl
import be.marche.www.utils.makePhoneCall2

/**
 *
 */
class Actions(navController: NavHostController) {
    val home: () -> Unit = {
        navController.navigate(Router.Routes.Home)
    }
    val listNews: () -> Unit = {
        navController.navigate(Router.Routes.News)
    }
    val newsShow: (newsId: Int) -> Unit = { newsId ->
        navController.navigate("newsShow/$newsId")
    }
    val listEvents: () -> Unit = {
        navController.navigate(Router.Routes.Agenda)
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
    val urgenceList: () -> Unit = {
        navController.navigate(Router.Routes.Urgence)
    }
    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
    val callNumber: (context: Context, number: String) -> Unit = { context, number ->
        makePhoneCall2(context, number)
    }
    val openUrl: (context: Context, url: String) -> Unit = { context, url ->
        makeOpenUrl(context, url)
    }
}