package be.marche.www.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.marche.bottin.model.Category
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.model.Classement
import kotlinx.coroutines.flow.Flow

@Dao
interface BottinDao {

    /**
     * Category
     */
    @Query("SELECT * FROM category ORDER BY name")
    fun findAllCategories(): List<Category>

    @Query("SELECT * FROM category WHERE id = :categoryId")
    suspend fun findCategoryById(categoryId: Int): Category

    @Query("SELECT * FROM category WHERE parent_id = 0 OR parent_id IS NULL ORDER BY name")
    fun findRootsCategories(): List<Category>

    @Query("SELECT * FROM category WHERE parent_id = 0 OR parent_id IS NULL ORDER BY name")
    fun findRootsCategoriesFlow(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE parent_id = :categoryId ORDER BY name")
    fun findChildren(categoryId: Int): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<Category>)

    /**
     * Fiche
     */
    @Query("SELECT * FROM fiche")
    fun findAllFiches(): List<Fiche>

    @Query("SELECT * FROM fiche WHERE id = :ficheId")
    suspend fun findFicheById(ficheId: Int): Fiche

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFiches(fiches: List<Fiche>)

    @Query("SELECT * FROM fiche WHERE id IN (:ids)")
    fun findFichesByIds(ids: List<Int>): List<Fiche>

    /**
     * Classement
     */
    @Query("SELECT * FROM classement WHERE category_id = :categorieId")
    suspend fun getFichesByCategoryId(categorieId: Int): List<Classement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassements(classements: List<Classement>)

    @Query("SELECT * FROM classement WHERE fiche_id = :ficheId")
    suspend fun findClassmentsByFicheId(ficheId: Int): List<Classement>

}