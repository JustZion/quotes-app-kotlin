package com.example.quotesapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RefrofitInstance {

    private val BASE_URL = "https://zenquotes.io/api/random"

    private fun retrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val quoteApi: QuoteApp = retrofitInstance().create(QuoteApp::class.java)


}