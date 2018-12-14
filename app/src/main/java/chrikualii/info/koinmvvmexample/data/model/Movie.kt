package chrikualii.info.koinmvvmexample.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by chirikualii on 10/12/18
 * github.com/chirikualii
 */
data class Movie(

    @field:SerializedName("id")
    var idMovie: String? = null,

    @field:SerializedName("title")
    var title: String? = null,

    @field:SerializedName("original_title")
    var originalTitle: String? = null,

    @field:SerializedName("release_date")
    var releaseDate: String? = null,

    @field:SerializedName("overview")
    var overview: String? = null,

    @field:SerializedName("poster_path")
    var posterPath: String? = null,

    @field:SerializedName("vote_average")
    var voteAverage: String? = null
)