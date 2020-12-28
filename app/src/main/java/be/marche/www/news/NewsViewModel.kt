package be.marche.www.news

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import be.marche.www.event.repository.NewsRepository
import be.marche.www.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository,
) : ViewModel() {

    val allNews: LiveData<List<News>> = liveData {
        val newsList = loadNewsFromRemote()
        emit(newsList)
    }

    suspend fun loadNewsFromRemote(): List<News> {
        return newsRepository.findAllNewsFromRemote()
    }

    fun loadNews(): LiveData<List<News>> =
        liveData(Dispatchers.IO) {
            emit(newsRepository.findAllNews())
        }

    fun insertNews(news: List<News>) {
        viewModelScope.launch {
            newsRepository.insertNews(news)
        }

    }

}
