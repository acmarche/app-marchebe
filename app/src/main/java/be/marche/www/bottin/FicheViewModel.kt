package be.marche.www.bottin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.repository.ClassementRepository
import be.marche.www.bottin.repository.FicheRepository
import be.marche.www.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FicheViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val ficheRepository: FicheRepository,
    private val classementRepository: ClassementRepository,
) : ViewModel() {

    val allFichesFromRemote: LiveData<List<Fiche>> = liveData {
        emit(loadAllFichesFromRemote()) //is a suspend function.
    }

    suspend fun loadAllFichesFromRemote(): List<Fiche> =
        ficheRepository.loadAllFromRemote()


    fun findAllFichesAsLive(): LiveData<List<Fiche>> =
        liveData(Dispatchers.IO) {
            emit(ficheRepository.findAllFiches())
        }

    fun findAllFiches(): List<Fiche> {
        return ficheRepository.findAllFiches()
    }

    fun findFichesByCategory(categoryId: Int): LiveData<List<Fiche>> {
        return liveData {
            val list: MutableList<Fiche> = ArrayList()
            val classements = classementRepository.findByCategoryId(categoryId)
            for (classement in classements) {
                list.add(ficheRepository.findById(classement.fiche_id))
            }
            emit(list)
        }
    }

    fun insertFiches(fiches: List<Fiche>) {
        viewModelScope.launch {
            ficheRepository.insertAll(fiches)
        }
    }

    fun findByIdAsLive(ficheId: Int): LiveData<Fiche> = liveData {
        emit(ficheRepository.findById(ficheId))
    }

}