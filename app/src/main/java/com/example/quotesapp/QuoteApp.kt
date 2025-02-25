package com.example.quotesapp

import retrofit2.Response
import retrofit2.http.GET

interface QuoteApp {

    @GET("random")
    suspend fun getRandomQuotes(): Response<List<DataResponse>>
}