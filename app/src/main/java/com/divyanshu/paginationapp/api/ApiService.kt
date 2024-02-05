package com.divyanshu.paginationapp.api

import com.divyanshu.paginationapp.model.Cartoon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("character/")
    suspend fun getCartoonsWithPagination(@Query("page") page: Int): Response<Cartoon>
}