package com.example.logique_test.domain.repository

import androidx.paging.PagingData
import com.example.logique_test.core.data.State
import com.example.logique_test.domain.uimodel.UserUiModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserList(): Flow<PagingData<UserUiModel>>
}