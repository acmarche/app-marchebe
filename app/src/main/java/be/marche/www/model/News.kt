package be.marche.www.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity()
data class News(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("ID")
    val id: Int,
    val intitule: String,
    val date: String,
    val content: String,
    val extrait: String,
    val url: String,
    val thumbnail: String,
)