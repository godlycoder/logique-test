package com.example.logique_test.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.logique_test.core.utils.Constants
import com.example.logique_test.data.datasources.user.UserRemoteDataSource
import com.example.logique_test.data.paging.UserPagingSource
import com.example.logique_test.domain.repository.UserRepository
import com.example.logique_test.domain.uimodel.UserUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun getUserList(): Flow<PagingData<UserUiModel>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.MAX_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                UserPagingSource(userRemoteDataSource)
            }
        ).flow
    }
}