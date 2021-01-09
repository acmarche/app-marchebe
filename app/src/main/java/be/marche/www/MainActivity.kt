package be.marche.www

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import be.marche.www.bottin.CategoryViewModel
import be.marche.www.bottin.FicheViewModel
import be.marche.www.event.EventViewModel
import be.marche.www.navigation.NavGraph
import be.marche.www.news.NewsViewModel
import be.marche.www.sync.SyncViewModel
import be.marche.www.ui.MarcheComposeTheme
import be.marche.www.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val eventViewModel: EventViewModel by viewModels()
    private val ficheViewModel: FicheViewModel by viewModels()
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val syncViewModel: SyncViewModel by viewModels()
    @Inject
    lateinit var navGraph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        syncContent()

        setContent {
            MarcheComposeTheme {
                navGraph.RegisterRoutes(
                    newsViewModel,
                    eventViewModel,
                    ficheViewModel,
                    categoryViewModel
                )
            }
        }
    }

    private fun syncContent() {

        NetworkUtils.getNetworkLiveData(application).observe(this, { connected ->
            when (connected) {
                false -> Toast
                    .makeText(this, getString(R.string.no_internet), Toast.LENGTH_LONG)
                    .show()
                true -> {
                    syncViewModel.syncNews()
                    syncViewModel.syncEvents()
                    syncViewModel.syncFiches()
                    syncViewModel.syncCategories()
                    syncViewModel.syncClassements()
                }
            }
        })
    }
}
