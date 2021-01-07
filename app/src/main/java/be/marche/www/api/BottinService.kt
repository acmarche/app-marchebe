package be.marche.www.api

import be.marche.bottin.model.Categorie
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.model.Classement
import retrofit2.http.GET
import retrofit2.http.Path

interface BottinService {

    /**
     * Category
     */
    @GET("bottin/categories")
    suspend fun loadAllCategories(
    ): List<Categorie>

    /**
     * Fiches
     */
    @GET("bottin/fichesandroid")
    suspend fun loadAllFiches(
    ): List<Fiche>

    @GET("fiches/rubrique/{id}")
    suspend fun findFichesByCategory(
        @Path("id") categoryId: Int
    ): List<Fiche>

    @GET("bottin/classements")
    suspend fun loadAllClassements(
    ): List<Classement>

}