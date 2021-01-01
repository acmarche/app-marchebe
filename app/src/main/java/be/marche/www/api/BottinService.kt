package be.marche.www.api

import be.marche.bottin.model.Category
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.model.Classement
import retrofit2.http.GET
import retrofit2.http.Path

interface BottinService {

    /**
     * Category
     */
    @GET("bottin/rubriques")
    suspend fun loadAllCategories(
    ): List<Category>

    /**
     * Fiches
     */
    @GET("bottin/fiches")
    suspend fun loadAllFiches(
    ): List<Fiche>

    @GET("fiches/rubrique/{id}")
    suspend fun findFichesByCategory(
        @Path("id") categoryId: Int
    ): List<Fiche>

    @GET("allclassements")
    suspend fun loadAllClassements(
    ): List<Classement>

}