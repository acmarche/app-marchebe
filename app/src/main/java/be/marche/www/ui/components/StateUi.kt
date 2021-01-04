package be.marche.www.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import be.marche.www.api.UiState
import be.marche.www.sync.SyncViewModel
import timber.log.Timber


@Composable
fun CheckSync(syncViewModel: SyncViewModel) {

    val uiState by syncViewModel.uiState().observeAsState(initial = null)

    if (uiState != null) {

        when (uiState) {
            is UiState.Loading -> {
                //                Timber.w("zeze loading")
            }
            is UiState.Success -> {
                val news = (uiState as UiState.Success).recentVersions
                //  newsViewModel.insertNews(news)
            }
            is UiState.Error -> {
                AlertMessage("Error ${(uiState as UiState.Error).message}")
                Timber.w("zeze error")
            }
        }
    }
}