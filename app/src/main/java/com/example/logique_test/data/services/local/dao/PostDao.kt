package com.example.logique_test.data.services.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.logique_test.data.dto.PostDTO
import com.example.logique_test.data.services.local.entity.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    suspend fun getAll(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PostEntity)

    @Query("SELECT * FROM posts WHERE postId = :postId")
    suspend fun getByPostId(postId: String): PostEntity?

    @Query("DELETE FROM posts WHERE postId = :postId")
    suspend fun deleteByPostId(postId: String)
}