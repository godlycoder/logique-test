package com.example.logique_test.data.services.remote

import com.example.logique_test.core.network.Response
import com.example.logique_test.data.dto.PostDTO
import com.example.logique_test.data.dto.ProfileDTO
import com.example.logique_test.data.dto.UserDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("user?limit=10")
    suspend fun fetchAllUsers(@Query("page") page: Int): Response<List<UserDTO>>

    @GET("user/{userId}/post?limit=10")
    suspend fun fetchUserPost(@Path("userId") userId: String, @Query("page") page: Int): Response<List<PostDTO>>

    @GET("user/{userId}")
    suspend fun fetchUserProfile(@Path("userId") userId: String): ProfileDTO

}
