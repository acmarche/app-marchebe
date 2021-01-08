package be.marche.www.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import be.marche.www.api.UiState
import be.marche.www.sync.SyncViewModel

@Composable
fun CheckSync(syncViewModel: SyncViewModel) {

    val uiState by syncViewModel.uiState().observeAsState(initial = null)

    if (uiState != null) {

        when (uiState) {
            is UiState.Loading -> {

            }
            is UiState.Success -> {
                val news = (uiState as UiState.Success).value
                //  newsViewModel.insertNews(news)
            }
            is UiState.Error -> {
                AlertMessage("Error ${(uiState as UiState.Error).message}")
            }
        }
    }
}