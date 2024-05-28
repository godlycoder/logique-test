package com.example.logique_test.data.datasources

import com.example.logique_test.data.dto.ProfileDTO
import com.example.logique_test.data.services.remote.ApiServices
import javax.inject.Inject

class ProfileRemoteDataSource @Inject constructor(
    private val apiServices: ApiServices,
){
    suspend fun getUserProfile(userId: String): ProfileDTO {
        return apiServices.fetchUserProfile(userId)
    }

}
