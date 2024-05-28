package com.example.logique_test.data.services.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val postId: String,
    val imageUrl: String,
    val title: String,
    val desc: String,
    val tags: List<String>,
    val likesCount: Int,
    val isLike: Boolean
)