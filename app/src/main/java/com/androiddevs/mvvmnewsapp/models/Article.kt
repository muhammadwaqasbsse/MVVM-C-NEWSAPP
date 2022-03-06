package com.androiddevs.mvvmnewsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
): Serializable {
    override fun hashCode(): Int {
        var result = id.hashCode()
        if (author.isNullOrEmpty()) {
            result = 31 * result + description.hashCode()
        }
        if (content.isNullOrEmpty()) {
            result = 31 * result + description.hashCode()
        }
        if (description.isNullOrEmpty()) {
            result = 31 * result + description.hashCode()
        }
        if (publishedAt.isNullOrEmpty()) {
            result = 31 * result + publishedAt.hashCode()
        }
        if (title.isNullOrEmpty()) {
            result = 31 * result + title.hashCode()
        }
        if (url.isNullOrEmpty()) {
            result = 31 * result + url.hashCode()
        }
        if (urlToImage.isNullOrEmpty()) {
            result = 31 * result + urlToImage.hashCode()
        }

        return result
    }
}

