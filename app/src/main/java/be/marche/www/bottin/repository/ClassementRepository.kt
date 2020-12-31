package be.marche.www.bottin.repository

import be.marche.bottin.model.Category
import be.marche.www.api.BottinService
import be.marche.www.bottin.model.Classement
import be.marche.www.database.BottinDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClassementRepository @Inject constructor(
    private val bottinService: BottinService,
    private val bottinDao: BottinDao
) {

    suspend fun loadAllFromRemote() = bottinService.loadAllClassements()

    suspend fun insertAll(classements: List<Classement>) {
        bottinDao.insertClassements(classements)
    }

    suspend fun findByFicheId(ficheId: Int): List<Classement> {
        return bottinDao.findClassmentsByFicheId(ficheId)
    }

    fun findByCategoryId(categorieId: Int): List<Classement> {
        return bottinDao.getFichesByCategoryId(categorieId)
    }

    suspend fun insertClassements(classements: List<Classement>) {
        withContext(Dispatchers.IO) {
            bottinDao.insertClassements(classements)
        }
    }
}