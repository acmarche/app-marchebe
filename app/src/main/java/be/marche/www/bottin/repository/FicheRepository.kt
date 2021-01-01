package be.marche.www.bottin.repository

import be.marche.bottin.model.Fiche
import be.marche.www.api.BottinService
import be.marche.www.database.BottinDao
import javax.inject.Inject

class FicheRepository @Inject constructor(
    private val bottinService: BottinService,
    private val bottinDao: BottinDao
) {

    suspend fun loadAllFromRemote() = bottinService.loadAllFiches()

    fun findAllFiches() = bottinDao.findAllFiches()

    suspend fun insertAll(fiches: List<Fiche>) {
        bottinDao.insertFiches(fiches)
    }

    suspend fun findById(ficheId: Int): Fiche {
        return bottinDao.findFicheById(ficheId)
    }

    suspend fun getFichesByCategory(categoryId: Int) =
        bottinService.findFichesByCategory(categoryId)

}
