package chrikualii.info.koinmvvmexample.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable


/**
 * Created by chirikualii on 11/12/18
 * github.com/chirikualii
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie_table")
    fun getMovie(): Flowable<List<MovieEntity>>
}