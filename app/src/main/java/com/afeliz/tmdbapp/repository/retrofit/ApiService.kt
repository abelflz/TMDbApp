package com.afeliz.tmdbapp.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    var api: ApiMethods

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org").addConverterFactory(GsonConverterFactory.create()).build()
        api = retrofit.create(ApiMethods::class.java)
    }
}