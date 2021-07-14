package com.example.moviedb.network.interfaces

import com.example.moviedb.models.MovieData
import com.example.moviedb.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("discover/movie?with_genres=18&api_key=${Constants.API_KEY}&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate")
   suspend fun getMovies(@Query("page") page: Int): MovieData
}