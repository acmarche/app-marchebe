package be.marche.www.bottin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
   /* foreignKeys = [
        ForeignKey(
            entity = Fiche::class,
            parentColumns = ["id"],
            childColumns = ["fiche_id"]
        ),
        ForeignKey(
            entity = Categorie::class,
            parentColumns = ["id"],
            childColumns = ["category_id"]
        )
    ]*/
)
data class Classement(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fiche_id: Int,
    val category_id: Int,
    val principal: Boolean
)
