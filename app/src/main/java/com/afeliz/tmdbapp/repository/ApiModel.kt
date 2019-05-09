package com.afeliz.tmdbapp.repository

data class Movies(val results:List<MovieList>)

data class MovieList(
    val id:Int,
    val title:String,
    val poster_path:String
)

data class MovieResult(val results:List<Movie>)
data class Movie(
    val key: String
)