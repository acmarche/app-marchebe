package be.marche.www.bottin.repository

import be.marche.www.api.BottinService
import be.marche.www.bottin.model.Classement
import be.marche.www.database.BottinDao
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

    suspend fun findByCategoryId(categorieId: Int): List<Classement> {
        return bottinDao.getFichesByCategoryId(categorieId)
    }

}