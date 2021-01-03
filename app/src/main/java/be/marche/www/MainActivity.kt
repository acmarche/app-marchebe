package be.marche.www

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.ClassementViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.event.EventViewModel
import be.marche.www.navigation.RegisterRoutes
import be.marche.www.news.NewsViewModel
import be.marche.www.ui.components.MarcheComposeTheme
import be.marche.www.utils.ConnectivityLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val eventViewModel: EventViewModel by viewModels()
    private val ficheViewModel: FicheViewModel by viewModels()
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val classementViewModel: ClassementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        syncContent()

        setContent {
            MarcheComposeTheme {
                RegisterRoutes(
                    newsViewModel,
                    eventViewModel,
                    ficheViewModel,
                    categoryViewModel,
                    classementViewModel
                )
            }
        }
    }

    private fun syncContent() {
        ConnectivityLiveData(application).observe(this, { connected ->
            if (connected == true) {
                newsViewModel.allNewsFromRemote
                    .observe(this, { news ->
                        newsViewModel.insertNews(news)
                    })

                eventViewModel.allEventFromRemote
                    .observe(this, { event ->
                        eventViewModel.insertEvent(event)
                    })

                ficheViewModel.allFichesFromRemote
                    .observe(this, { fiches ->
                        ficheViewModel.insertFiches(fiches)
                    })

                categoryViewModel.allCategoriesFromRemote
                    .observe(this, { categories ->
                        categoryViewModel.insertCategories(categories)
                    })

                classementViewModel.allClassementsFromRemote
                    .observe(this, { classements ->
                        classementViewModel.insertClassements(classements)
                    })
            }
        })
    }
}
