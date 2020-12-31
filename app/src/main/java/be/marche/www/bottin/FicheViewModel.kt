package be.marche.www.bottin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.repository.FicheRepository

class FicheViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val ficheRepository: FicheRepository,
) : ViewModel() {

    val fiches: LiveData<List<Fiche>> = liveData {
        emit(ficheRepository.findAllFiches()) //is a suspend function.
    }

    fun getFichesByCategory(categoryId: Int): LiveData<Fiche> {
        return liveData {
            emit(ficheRepository.findById(categoryId))
        }
    }
}