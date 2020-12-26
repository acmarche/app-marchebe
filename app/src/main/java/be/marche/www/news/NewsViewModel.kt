package be.marche.www.news

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import be.marche.www.event.repository.NewsRepository
import be.marche.www.model.News

class NewsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository,
) : ViewModel() {

    val allNews: LiveData<List<News>> = liveData {
        val newsList = loadNews()
        emit(newsList)
    }

    suspend fun loadNews(): List<News> {
        return newsRepository.findAllNews()
    }

}
