package be.marche.www.api

import be.marche.bottin.model.Category
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.model.Classement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BottinService {
    @GET("rubriques")
    suspend fun getAllCategories(
    ): List<Category>

    @GET("fiches")
    suspend fun getAllFiches(
    ): List<Fiche>

    @GET("fiches/rubrique/{id}")
    suspend fun getFichesByCategory(
        @Path("id") categoryId: Int
    ): List<Fiche>

    @GET("allclassements")
    suspend fun getAllClassements(
    ): List<Classement>

}