package chrikualii.info.koinmvvmexample.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull


/**
 * Created by chirikualii on 11/12/18
 * github.com/chirikualii
 */
@Entity(tableName = "movie_table")
data class MovieEntity(
    @NonNull
    @PrimaryKey
    var idMovie: String,
    var title: String? = null,
    var originalTitle: String? = null,
    var releaseDate: String? = null,
    var overview: String? = null,
    var posterPath: String? = null,
    var voteAverage: String? = null
)
