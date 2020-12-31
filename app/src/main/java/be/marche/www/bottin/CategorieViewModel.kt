package be.marche.www.bottin

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import be.marche.bottin.model.Category
import be.marche.www.bottin.repository.CategoryRepository

class CategoryViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    var categorieSelected: Category? = null
    var parentCategory: Category? = null

    var categories: LiveData<List<Category>> = liveData {
        val emps = categoryRepository.findAll()
        emit(emps)
    }

    fun findRootsCategorys(): LiveData<List<Category>> = liveData {
        emit(categoryRepository.findRootsCategories())
    }

    fun findChilds(categorie: Category): LiveData<List<Category>> = liveData {
        emit(categoryRepository.findChilds(categorie))
    }

    fun findCagoryById(categorieId: Int): LiveData<Category> = liveData {
        emit(categoryRepository.findById(categorieId))
    }

}