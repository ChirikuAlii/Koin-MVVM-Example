package chrikualii.info.koinmvvmexample.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by chirikualii on 10/12/18
 * github.com/chirikualii
 */
data class MovieResponse(
    @field:SerializedName("results")
    var result: List<Movie>? = null,

    @field:SerializedName("page")
    var page: String? = null,

    @field:SerializedName("total_results")
    var totalResults: String? = null,

    @field:SerializedName("total_pages")
    var totalPages: String? = null

)