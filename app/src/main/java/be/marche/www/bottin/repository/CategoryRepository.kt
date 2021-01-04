package be.marche.www.bottin.repository

import be.marche.bottin.model.Category
import be.marche.www.api.BottinService
import be.marche.www.database.BottinDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val bottinService: BottinService,
    private val bottinDao: BottinDao
) {

    suspend fun loadAllFromRemote() = bottinService.loadAllCategories()

    fun findAll() = bottinDao.findAllCategories()

    suspend fun findById(categoryId: Int): Category {
        return bottinDao.findCategoryById(categoryId)
    }

    fun findRootsCategories(): List<Category> {
        return bottinDao.findRootsCategories()
    }

    suspend fun findChildren(categoryId: Int): List<Category> {
        return bottinDao.findChildren(categoryId)
    }

    suspend fun insertAll(categories: List<Category>) {
        bottinDao.insertCategories(categories)
    }

    val categoriesFlow: Flow<List<Category>> = bottinDao.findRootsCategoriesFlow()

}

