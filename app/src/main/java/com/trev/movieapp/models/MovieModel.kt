package com.trev.movieapp.models

import android.os.Parcel
import android.os.Parcelable

data class MovieModel (

    var id: Int,
    var original_title: String?,
    var poster_path: String?,
    var release_date: String?,
    var vote_average: Double,
    var movie_overview: String?,
    var runtime: Int?

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(original_title)
        parcel.writeString(poster_path)
        parcel.writeString(release_date)
        parcel.writeDouble(vote_average)
        parcel.writeString(movie_overview)
        parcel.writeValue(runtime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieModel> {
        override fun createFromParcel(parcel: Parcel): MovieModel {
            return MovieModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieModel?> {
            return arrayOfNulls(size)
        }
    }
}

//class MovieModel{
//    private var id: Int? = null
//    private var original_title: String? = null
//    private var poster_path: String? = null
//    private var release_date: String? = null
//    private var vote_average: Double? = null
//    private var movie_overview: String? = null
//}