package com.example.logique_test.data

import com.example.logique_test.BuildConfig
import com.example.logique_test.core.network.okHttpClient
import com.example.logique_test.core.network.retrofitClient
import com.example.logique_test.data.services.remote.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpClient() : OkHttpClient {
        val builder = okHttpClient(BuildConfig.DEBUG)
        builder.addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .addHeader("app-id", BuildConfig.APP_ID)
                .build()
            chain.proceed(newRequest)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesApiServices(okHttpClient: OkHttpClient): ApiServices = retrofitClient(
        okHttpClient, BuildConfig.BASE_URL
    ).create(ApiServices::class.java)

}