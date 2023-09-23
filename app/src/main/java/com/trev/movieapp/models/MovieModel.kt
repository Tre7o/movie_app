package com.trev.movieapp.models

data class MovieModel (

    var id: Int,
    var original_title: String,
    var poster_path: String,
    var release_date: String,
    var vote_average: Double,
    var movie_overview: String,

)

//class MovieModel{
//    private var id: Int? = null
//    private var original_title: String? = null
//    private var poster_path: String? = null
//    private var release_date: String? = null
//    private var vote_average: Double? = null
//    private var movie_overview: String? = null
//}