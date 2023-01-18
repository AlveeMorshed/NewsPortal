package com.moinul.newsportal.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "bookmark_table")
data class Bookmark(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String?,
    @PrimaryKey
    val url: String?,
    val urlToImage: String?
):Parcelable
