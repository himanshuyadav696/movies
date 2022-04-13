package com.example.moviess

import androidx.annotation.Keep
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailInterface {
    @GET("3/movie/{movie_id}?api_key=d0f88cda76de892dbcbfa13076fc9006")
    fun getMyData4(@Path("movie_id") movie_id:Int?):Call<MovieDetails>

    //fun getname(@Path("name") name:String?):Call<MovieDetails.ProductionCompany>
}