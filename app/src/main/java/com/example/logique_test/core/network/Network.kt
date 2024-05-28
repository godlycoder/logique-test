package com.example.logique_test.core.network

import com.example.logique_test.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun retrofitClient(okHttpClient : OkHttpClient, baseUrl : String): Retrofit =
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()

fun okHttpClient(debug: Boolean) : OkHttpClient.Builder {
        val client = OkHttpClient.Builder()
        client.readTimeout(60, TimeUnit.SECONDS)
        client.connectTimeout(60, TimeUnit.SECONDS)
        if (debug){
                val loggingInterceptor = HttpLoggingInterceptor()
                val convertInterceptor = object : Interceptor {
                        override fun intercept(chain: Interceptor.Chain): Response {
                                val response = chain.request().body
                                if (response is List<*>) {
                                        return Response.Builder()
                                                .body(response[0] as ResponseBody)
                                                .build()
                                }

                                return chain.proceed(chain.request())
                        }

                }

                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(loggingInterceptor)
                        .addInterceptor(convertInterceptor)
        }
        return client
}
