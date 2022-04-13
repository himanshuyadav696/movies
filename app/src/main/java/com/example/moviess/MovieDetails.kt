package com.example.moviess
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class MovieDetails(
    val adult: Boolean?=null,
    val backdrop_path: String,
    val belongs_to_collection: String,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) :Parcelable{
    @Parcelize
    data class Genre(
        val id: Int,
        val name: String
    ):Parcelable

    @Parcelize
    data class ProductionCompany(
        val id: Int,
        val logo_path: String,
        val name: String,
        val origin_country: String
    ):Parcelable

    @Parcelize
    data class ProductionCountry(
        val iso_3166_1: String,
        val name: String
    ):Parcelable

    @Parcelize
    data class SpokenLanguage(
        val english_name: String,
        val iso_639_1: String,
        val name: String
    ):Parcelable
}