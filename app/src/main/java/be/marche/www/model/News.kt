package be.marche.www.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val intitule: String,
    val date: String,
    val content: String,
    val extrait: String,
    val url: String,
    val thumbnail: String?,
    val image: String?,
)