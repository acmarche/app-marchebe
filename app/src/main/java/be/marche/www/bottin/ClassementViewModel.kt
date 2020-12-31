package be.marche.www.bottin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import be.marche.www.bottin.model.Classement
import be.marche.www.bottin.repository.ClassementRepository

class ClassementViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val classementRepository: ClassementRepository,
) : ViewModel() {


    fun getFichesByCategoryId(categoryId: Int): LiveData<List<Classement>> {
        return liveData {
            emit(classementRepository.findByCategoryId(categoryId))
        }
    }

    fun getCategoriesByFicheId(categoryId: Int): LiveData<List<Classement>> {
        return liveData {
            emit(classementRepository.findByFicheId(categoryId))
        }
    }
}