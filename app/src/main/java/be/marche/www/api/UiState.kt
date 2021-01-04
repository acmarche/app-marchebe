package be.marche.www.api

import be.marche.www.model.News


sealed class UiState {
    object Loading : UiState()
    data class Success(val recentVersions: List<News>) : UiState()
    data class Error(val message: String) : UiState()
}

class OddList<T>(val list: List<T>) {
    fun oddItems(): List<T> {
        return list.filterIndexed { index, _ -> index % 2 == 1 }
    }
}