package be.marche.www.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val titre: String,
    val description: String,
    val date_fin: String,
    val date_debut: String,
    val date_affichage: String,
    val lieu_nom: String?,
    val lieu_num: String?,
    val adresse: String?,
    val localite: String?,
    val cp: String?,
    val nom: String?,
    val latitude: String?,
    val longitude: String?,
    val info: String?,
    val telephone: String?,
    val gsm: String?,
    val website: String?,
    val email: String?,
    val fax: String?,
    val photo: String?
)