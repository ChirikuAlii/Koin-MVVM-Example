package chrikualii.info.koinmvvmexample.data.network

import chrikualii.info.koinmvvmexample.data.model.MovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by chirikualii on 10/12/18
 * github.com/chirikualii
 */
interface ApiService {

    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String): Flowable<MovieResponse>
}