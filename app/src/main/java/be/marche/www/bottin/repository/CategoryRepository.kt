package be.marche.www.bottin.repository

import be.marche.bottin.model.Categorie
import be.marche.www.api.BottinService
import be.marche.www.database.BottinDao
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val bottinService: BottinService,
    private val bottinDao: BottinDao
) {

    suspend fun loadAllFromRemote() = bottinService.loadAllCategories()

    suspend fun findAllCategories() = bottinDao.findAllCategories()

    suspend fun insertAll(categories: List<Categorie>) {
        bottinDao.insertCategories(categories)
    }

    suspend fun findById(categoryId: Int): Categorie {
        return bottinDao.findCategoryById(categoryId)
    }

    fun findRootsCategories(): List<Categorie> {
        return bottinDao.findRootsCategories()
    }

    suspend fun findChildren(categoryId: Int): List<Categorie> {
        return bottinDao.findChildren(categoryId)
    }

    // val categoriesFlow: Flow<List<Category>> = bottinDao.findRootsCategoriesFlow()

}

