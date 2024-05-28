package com.example.logique_test.domain.repository

import com.example.logique_test.core.data.State
import com.example.logique_test.domain.uimodel.ProfileUiModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getUserProfile(userId: String): Flow<State<ProfileUiModel>>
}
