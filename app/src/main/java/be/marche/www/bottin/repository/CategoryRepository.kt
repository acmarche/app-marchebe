package be.marche.www.bottin.repository

import be.marche.bottin.model.Category
import be.marche.www.api.BottinService
import be.marche.www.database.BottinDao
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val bottinService: BottinService,
    private val bottinDao: BottinDao
) {

    suspend fun loadAllCategoriesFromRemote() = bottinService.loadAllCategories()

    fun findAll() = bottinDao.findAllCategories()

    suspend fun insertCategories(categories: List<Category>) {
        bottinDao.insertCategories(categories)
    }

    suspend fun findById(categoryId: Int): Category {
        return bottinDao.findCagorieById(categoryId)
    }

    fun findRootsCategories(): List<Category> {
        return bottinDao.findRootsCategories()
    }

    fun findChilds(categorie: Category): List<Category> {
        return bottinDao.findChilds(categorie.id)
    }
}

