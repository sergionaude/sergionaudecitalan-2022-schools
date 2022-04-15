package com.example.sergionaudecitalan_2022_schools.Core

import com.example.sergionaudecitalan_2022_schools.repository.ISchoolsAPI
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Retrofit {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()

    /**
     * Retofit service
     */
    val retrofitService = retrofit2.Retrofit.Builder()
        .baseUrl("https://data.cityofnewyork.us")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build().create(ISchoolsAPI::class.java)

}


