package be.marche.www

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import be.marche.www.event.EventViewModel
import be.marche.www.event.ui.ListEventsComponent
import be.marche.www.navigation.Actions
import be.marche.www.navigation.Destinations.Agenda
import be.marche.www.navigation.Destinations.Home
import be.marche.www.navigation.Destinations.News
import be.marche.www.news.NewsViewModel
import be.marche.www.news.ui.ListNewsComponent
import be.marche.www.ui.HomeComponent
import be.marche.www.ui.MarcheComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Launch(newsViewModel, eventViewModel)

        }
    }
}

@Composable
fun Launch(newsViewModel: NewsViewModel, eventViewModel: EventViewModel) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    MarcheComposeTheme {
        NavHost(navController = navController, startDestination = Home) {
            composable(Home) {
                HomeComponent(
                    listNews = actions.listNews,
                    listEvents = actions.listEvents
                )
            }
            composable(News) {
                ListNewsComponent(
                    newsViewModel.allNews
                )
            }
            composable(Agenda) {
                ListEventsComponent(
                    eventViewModel.allEvents
                )
            }
        }
    }
}