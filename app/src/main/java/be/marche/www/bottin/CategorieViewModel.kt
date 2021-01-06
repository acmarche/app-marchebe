package be.marche.www.bottin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import be.marche.bottin.model.Category
import be.marche.www.bottin.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val allCategoriesFromRemote: LiveData<List<Category>> = liveData {
        emit(loadAllCategoriesFromRemote())
    }

    suspend fun loadAllCategoriesFromRemote(): List<Category> =
        categoryRepository.loadAllFromRemote()

    var categorieSelected: Category? = null
    var parentCategory: Category? = null

    fun findRootsCategorys(): LiveData<List<Category>> = liveData {
        emit(categoryRepository.findRootsCategories())
    }

    fun findChildren(categoryId: Int): LiveData<List<Category>> = liveData {
        emit(categoryRepository.findChildren(categoryId))
    }

    fun findById(categoryId: Int): LiveData<Category> = liveData {
        emit(categoryRepository.findById(categoryId))
    }

}