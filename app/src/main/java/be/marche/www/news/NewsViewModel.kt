package be.marche.www.news

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import be.marche.www.api.UiState
import be.marche.www.event.repository.NewsRepository
import be.marche.www.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository,
) : ViewModel() {

    val allNewsFromRemote: LiveData<List<News>> = liveData {
        val newsList = loadNewsFromRemote()
        emit(newsList)
    }

    suspend private fun loadNewsFromRemote(): List<News> {
        return newsRepository.loadAllNewsFromRemote()
    }

    fun findAllNews(): LiveData<List<News>> =
        liveData(Dispatchers.IO) {
            emit(newsRepository.findAllNews())
        }

    fun insertNews(news: List<News>) {
        viewModelScope.launch {
            newsRepository.insertNews(news)
        }
    }

    fun findById(newsId: Int): LiveData<News> = liveData {
        emit(newsRepository.findById(newsId))
    }



}

