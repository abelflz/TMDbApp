package com.afeliz.tmdbapp.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMethods {

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey:String = "8d1f9f956e67e1fa8369b7e25adf7f16"
    ) : Call<Movies>

    @GET("/3/movie/{id}/videos")
    fun getMovieTrailer(
        @Path("id") movieId:Int,
        @Query("api_key") apiKey:String = "8d1f9f956e67e1fa8369b7e25adf7f16"
    ) : Call<MovieResult>
}