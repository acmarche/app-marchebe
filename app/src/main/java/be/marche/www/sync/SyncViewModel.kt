package be.marche.www.sync

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import be.marche.www.api.UiState
import be.marche.www.bottin.repository.CategoryRepository
import be.marche.www.bottin.repository.ClassementRepository
import be.marche.www.bottin.repository.FicheRepository
import be.marche.www.event.repository.EventRepository
import be.marche.www.event.repository.NewsRepository
import kotlinx.coroutines.launch

class SyncViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository,
    private val eventRepository: EventRepository,
    private val categoryRepository: CategoryRepository,
    private val ficheRepository: FicheRepository,
    private val classementRepository: ClassementRepository
) : ViewModel() {

    fun uiState(): LiveData<UiState> = uiState

    protected val uiState: MutableLiveData<UiState> = MutableLiveData()

    fun syncNews() {
        //uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val news = newsRepository.loadAllNewsFromRemote()
                uiState.value = UiState.Success(news)
                newsRepository.insertNews(news)
            } catch (exception: Exception) {
                uiState.value =
                    UiState.Error("Erreur lors du chargement des actualités! : " + exception.message)
            }
        }
    }


}

