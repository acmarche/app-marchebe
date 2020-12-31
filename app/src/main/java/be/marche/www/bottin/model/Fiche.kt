package be.marche.bottin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Fiche(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val societe: String,
    val rue: String?,
    val numero: String?,
    val cp: String?,
    val localite: String?,
    val website: String?,
    val email: String?,
    val telephone: String?,
    val telephone_autre: String?,
    val fax: String?,
    val gsm: String?,
    val fonction: String?,
    val civilite: String?,
    val nom: String?,
    val prenom: String?,
    val contact_rue: String?,
    val contact_num: String?,
    val contact_cp: String?,
    val contact_localite: String?,
    val contact_email: String?,
    val contact_telephone: String?,
    val contact_telephone_autre: String?,
    val contact_fax: String?,
    val contact_gsm: String?,
    val comment1: String?,
    val comment2: String?,
    val comment3: String?,
    val midi: Boolean,
    val pmr: Boolean,
    val centreville: Boolean,
    val facebook: String?,
    val google_plus: String?,
    val twitter: String?,
    val logo: String?,
    val longitude: String?,
    val latitude: String?
)

/*@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Fiche::class,
            parentColumns = ["id"],
            childColumns = ["fiche_id"]
        )
    ]
)
data class Photo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fiche_id: Int,
    val url: String
)*/