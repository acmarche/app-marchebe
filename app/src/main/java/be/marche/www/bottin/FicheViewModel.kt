package be.marche.www.bottin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.repository.ClassementRepository
import be.marche.www.bottin.repository.FicheRepository

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

    fun findFichesByCategory(categoryId: Int): LiveData<List<Fiche>> {
        return liveData {
            val fiches: MutableList<Fiche> = mutableListOf()
            val classements = classementRepository.findByCategoryId(categoryId)
            for (classement in classements) {
                val fiche = ficheRepository.findById(classement.fiche_id)
                if (fiche != null) {
                    fiches.add(fiche)
                }
            }
            emit(fiches)
        }
    }

    fun findAll(): LiveData<List<Fiche>> = liveData {
        emit(ficheRepository.findAllFiches())
    }

    fun findByIdAsLive(ficheId: Int): LiveData<Fiche?> = liveData {
        emit(ficheRepository.findById(ficheId))
    }

}