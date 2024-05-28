package com.example.logique_test.core.network

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("data") val data: T,
    @SerializedName("total") val total: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("limit") val limit: Int,
)