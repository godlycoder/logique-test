package com.example.logique_test.data.datasources

import com.example.logique_test.data.services.local.dao.PostDao
import com.example.logique_test.data.services.local.entity.PostEntity
import javax.inject.Inject

class PostLocalDataSource @Inject constructor(
    private val postDao: PostDao
){
    suspend fun likePost(entity: PostEntity, like: Boolean) : Boolean {
        val foundEntity = postDao.getByPostId(entity.postId)

        if (foundEntity != null) {
            if (!like) {
                postDao.insert(entity)
            } else {
                postDao.deleteByPostId(entity.postId)
            }
        }

        return !like
    }

    suspend fun getAllPost(): List<PostEntity> {
        return postDao.getAll()
    }

}
