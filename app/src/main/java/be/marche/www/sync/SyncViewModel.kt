package be.marche.www.sync

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import be.marche.www.api.Resource
import be.marche.www.api.UiState
import be.marche.www.bottin.repository.CategoryRepository
import be.marche.www.bottin.repository.ClassementRepository
import be.marche.www.bottin.repository.FicheRepository
import be.marche.www.event.repository.EventRepository
import be.marche.www.event.repository.NewsRepository
import be.marche.www.model.Event
import be.marche.www.model.News
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.launch

class SyncViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val newsRepository: NewsRepository,
    private val eventRepository: EventRepository,
    private val categoryRepository: CategoryRepository,
    private val ficheRepository: FicheRepository,
    private val classementRepository: ClassementRepository
) : ViewModel() {

    fun uiState(): LiveData<UiState<List<News>>> = uiState
    protected val uiState: MutableLiveData<UiState<List<News>>> = MutableLiveData()
    protected val ressourceState: MutableLiveData<Resource<List<Event>>> = MutableLiveData()

    fun syncNews() {
        //uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val news = newsRepository.loadAllFromRemote()
                uiState.value = UiState.Success(news)
                newsRepository.insertAll(news)
            } catch (exception: Exception) {
                FirebaseCrashlytics.getInstance().log("sync actu"+exception.message)
                uiState.value =
                    UiState.Error("Erreur lors du chargement des actualités! : " + exception.message)
            }
        }
    }

    fun syncEvents() {
        //uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val events = eventRepository.loadAllFromRemote()
                ressourceState.value = Resource.Success(events)
                eventRepository.insertAll(events)
            } catch (exception: Exception) {
                uiState.value =
                    UiState.Error("Erreur lors du chargement des évènements! : " + exception.message)
            }
        }
    }

    fun syncFiches() {
        //uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val fiches = ficheRepository.loadAllFromRemote()
                //  uiState.value = UiState.Success(fiches)
                ficheRepository.insertAll(fiches)
            } catch (exception: Exception) {
                uiState.value =
                    UiState.Error("Erreur lors du chargement des fiches! : " + exception.message)
            }
        }
    }

    fun syncCategories() {
        //uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val categories = categoryRepository.loadAllFromRemote()
                // uiState.value = UiState.Success(categories)
                categoryRepository.insertAll(categories)
            } catch (exception: Exception) {
                uiState.value =
                    UiState.Error("Erreur lors du chargement des catégories! : " + exception.message)
            }
        }
    }

    fun syncClassements() {
        //uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val classements = classementRepository.loadAllFromRemote()
                //   uiState.value = UiState.Success(classements)
                classementRepository.insertAll(classements)
            } catch (exception: Exception) {
                uiState.value =
                    UiState.Error("Erreur lors du chargement des classements! : " + exception.message)
            }
        }
    }

}