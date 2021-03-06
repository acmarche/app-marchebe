package be.marche.www.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.marche.bottin.model.Categorie
import be.marche.bottin.model.Fiche
import be.marche.www.bottin.model.Classement
import kotlinx.coroutines.flow.Flow

@Dao
interface BottinDao {

    /**
     * Category
     */
    @Query("SELECT * FROM categorie ORDER BY name")
   suspend fun findAllCategories(): List<Categorie>

    @Query("SELECT * FROM categorie WHERE id = :categoryId")
    suspend fun findCategoryById(categoryId: Int): Categorie?

    @Query("SELECT * FROM categorie WHERE parent_id = 0 OR parent_id IS NULL ORDER BY name")
    fun findRootsCategories(): List<Categorie>

    @Query("SELECT * FROM categorie WHERE parent_id = 0 OR parent_id IS NULL ORDER BY name")
    fun findRootsCategoriesFlow(): Flow<List<Categorie>>

    @Query("SELECT * FROM categorie WHERE parent_id = :categoryId ORDER BY name")
    suspend fun findChildren(categoryId: Int): List<Categorie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<Categorie>)

    /**
     * Fiche
     */
    @Query("SELECT * FROM fiche")
  suspend  fun findAllFiches(): List<Fiche>

    @Query("SELECT * FROM fiche WHERE id = :ficheId")
    suspend fun findFicheById(ficheId: Int): Fiche?

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