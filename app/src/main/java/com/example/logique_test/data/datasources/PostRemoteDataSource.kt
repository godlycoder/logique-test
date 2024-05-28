package com.example.logique_test.data.datasources

import com.example.logique_test.core.network.Response
import com.example.logique_test.data.dto.PostDTO
import com.example.logique_test.data.services.remote.ApiServices
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
){
    suspend fun getUserPost(userId: String, page: Int): Response<List<PostDTO>> {
        return apiServices.fetchUserPost(userId, page)
    }

}
