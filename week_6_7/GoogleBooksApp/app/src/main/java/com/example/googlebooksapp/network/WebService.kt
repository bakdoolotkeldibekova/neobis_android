package com.example.googlebooksapp.network

import com.example.googlebooksapp.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("volumes?")
    fun getBooks(@Query("q") input: String?): Call<Result>
}