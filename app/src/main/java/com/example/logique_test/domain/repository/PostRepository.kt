package com.example.logique_test.domain.repository

import androidx.paging.PagingData
import com.example.logique_test.core.data.State
import com.example.logique_test.domain.uimodel.PostUiModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getFavoritesPost() : Flow<State<List<PostUiModel>>>
    suspend fun getUserPosts(userId: String): Flow<PagingData<PostUiModel>>
    suspend fun likePost(post: PostUiModel, like: Boolean): Flow<State<Boolean>>
}
