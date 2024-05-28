package com.example.logique_test.data.repository

import com.example.logique_test.core.data.State
import com.example.logique_test.data.datasources.ProfileRemoteDataSource
import com.example.logique_test.domain.mapper.toUiModel
import com.example.logique_test.domain.repository.ProfileRepository
import com.example.logique_test.domain.uimodel.ProfileUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource,
): ProfileRepository {
    override suspend fun getUserProfile(userId: String): Flow<State<ProfileUiModel>> {
        return flow {
            try {
                val result = remoteDataSource.getUserProfile(userId)
                emit(State.Success(result.toUiModel()))
            } catch (e: Exception) {
                emit(State.Failed(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}