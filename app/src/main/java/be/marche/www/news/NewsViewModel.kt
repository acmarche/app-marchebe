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

    /**
     *
     */

    fun uiState(): LiveData<UiState> = uiState
    protected val uiState: MutableLiveData<UiState> = MutableLiveData()

    fun performSingleNetworkRequest() {
     //   uiState.value = UiState.Loading
        viewModelScope.launch {
            Timber.w("zeze launch")
            try {
                val recentAndroidVersions = newsRepository.loadAllNewsFromRemote()
                uiState.value = UiState.Success(recentAndroidVersions)
                Timber.w("zeze success")

            } catch (exception: Exception) {
                Timber.w("zeze error " + exception)
                uiState.value = UiState.Error("Network Request failed! : " + exception.message)
            }
        }
    }


}

