package be.marche.www.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity()
data class News(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("ID")
    val id: Int,
    val post_author: String,
    val post_date: String,
    val post_content: String,
    val post_title: String,
    val post_excerpt: String,
    var post_status: String,
    val post_name: String,
    val post_modified: String,
    val guid: String,
    val post_thumbnail_url: String,
    val permalink: String,
    val blog_id: Int,
    val thumbnail: String,
    val thumbnail_moyen: String,
    val post_type: String
)