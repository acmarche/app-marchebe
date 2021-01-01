package be.marche.www.bottin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import be.marche.www.bottin.model.Classement
import be.marche.www.bottin.repository.ClassementRepository
import kotlinx.coroutines.launch

class ClassementViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val classementRepository: ClassementRepository,
) : ViewModel() {

    val allClassementsFromRemote: LiveData<List<Classement>> = liveData {
        emit(loadAllClassementsFromRemote())
    }

    suspend fun loadAllClassementsFromRemote(): List<Classement> =
        classementRepository.loadAllFromRemote()

    fun insertClassements(classements: List<Classement>) {
        viewModelScope.launch {
            classementRepository.insertAll(classements)
        }
    }


    fun getCategoriesByFicheId(categoryId: Int): LiveData<List<Classement>> {
        return liveData {
            emit(classementRepository.findByFicheId(categoryId))
        }
    }
}