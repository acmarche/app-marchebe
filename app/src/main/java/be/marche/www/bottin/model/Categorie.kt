package be.marche.bottin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity()
class Categorie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    @SerializedName("parent")
    val parent_id: Int?,
    val description: String?,
    val logo: String?,
    val logoBlanc: String?
)