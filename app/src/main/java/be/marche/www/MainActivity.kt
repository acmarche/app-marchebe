package be.marche.www

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import be.marche.www.event.EventViewModel
import be.marche.www.navigation.RegisterRoutes
import be.marche.www.news.NewsViewModel
import be.marche.www.ui.MarcheComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarcheComposeTheme {
                RegisterRoutes(newsViewModel, eventViewModel)
            }
        }
    }
}
