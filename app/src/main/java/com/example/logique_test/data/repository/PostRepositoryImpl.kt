package com.example.logique_test.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.logique_test.core.data.State
import com.example.logique_test.core.utils.Constants
import com.example.logique_test.data.datasources.PostLocalDataSource
import com.example.logique_test.data.datasources.PostRemoteDataSource
import com.example.logique_test.data.paging.PostPagingSource
import com.example.logique_test.domain.mapper.toEntity
import com.example.logique_test.domain.mapper.toUiModel
import com.example.logique_test.domain.repository.PostRepository
import com.example.logique_test.domain.uimodel.PostUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val remoteDataSource: PostRemoteDataSource,
    private val localDataSource: PostLocalDataSource
) : PostRepository {
    override suspend fun getFavoritesPost(): Flow<State<List<PostUiModel>>> {
        return flow {
            try {
                val result = localDataSource.getAllPost()
                val data = result.map { it.toUiModel() }
                emit(State.Success(data))
            } catch (e: Exception) {
                emit(State.Failed(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserPosts(userId: String): Flow<PagingData<PostUiModel>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                PostPagingSource(userId = userId, remoteDataSource)
            }
        ).flow
    }

    override suspend fun likePost(post: PostUiModel, like: Boolean): Flow<State<Boolean>> {
        return flow {
            try {
                val entity = post.toEntity()
                val result = localDataSource.likePost(entity, like)

                emit(State.Success(result))
            } catch (e: Exception) {
                emit(State.Failed(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}