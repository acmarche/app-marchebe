package be.marche.www.bottin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import be.marche.bottin.model.Categorie
import be.marche.www.bottin.repository.CategoryRepository

class CategoryViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val allCategoriesFromRemote: LiveData<List<Categorie>> = liveData {
        emit(loadAllCategoriesFromRemote())
    }

    suspend fun loadAllCategoriesFromRemote(): List<Categorie> =
        categoryRepository.loadAllFromRemote()

    var categorieSelected: Categorie? = null
    var parentCategory: Categorie? = null

    fun findRootsCategorys(): LiveData<List<Categorie>> = liveData {
        emit(categoryRepository.findRootsCategories())
    }

    fun findAll(): LiveData<List<Categorie>> = liveData {
        emit(categoryRepository.findAllCategories())
    }

    fun findChildren(categoryId: Int): LiveData<List<Categorie>> = liveData {
        emit(categoryRepository.findChildren(categoryId))
    }

    fun findByIdAsLiveData(categoryId: Int): LiveData<Categorie> = liveData {
        emit(categoryRepository.findById(categoryId))
    }

}