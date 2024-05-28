package com.example.logique_test.data.datasources.user

import com.example.logique_test.core.network.Response
import com.example.logique_test.data.services.remote.ApiServices
import com.example.logique_test.data.dto.UserDTO
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) {
    suspend fun getUserList(
        page: Int,
    ): Response<List<UserDTO>> {
        return apiServices.fetchAllUsers(page)
    }
}