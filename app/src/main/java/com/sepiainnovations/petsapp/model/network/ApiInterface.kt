package com.sepiainnovations.petsapp.model.network

import com.sepiainnovations.petsapp.model.data.WikiResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

//    @GET
//    suspend fun serviceCall(@Url path: String): Any

    @GET("?format=json&action=query&prop=extracts&exintro=&explaintext=")
    suspend fun getDescription(@Query("titles") articleTitle: String?): Result<WikiResponse>
}