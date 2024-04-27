package com.example.obanks.data.datasources.remote

import retrofit2.http.GET

interface OBanksService {
    @GET("challenge/banks")
    suspend fun fetchBanks(): OBanksResponse
}