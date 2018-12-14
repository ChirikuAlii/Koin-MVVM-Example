package chrikualii.info.koinmvvmexample.data.repo


import chrikualii.info.koinmvvmexample.data.db.MovieDao
import chrikualii.info.koinmvvmexample.data.db.MovieEntity
import chrikualii.info.koinmvvmexample.data.model.Movie
import chrikualii.info.koinmvvmexample.data.network.ApiService
import chrikualii.info.koinmvvmexample.util.API_KEY
import io.reactivex.Flowable


/**
 * Created by chirikualii on 10/12/18
 * github.com/chirikualii
 */
class MovieRepo(val apiService: ApiService, val movieDao: MovieDao) {

    fun getMovies(): Flowable<Movie> {
        return apiService.getMovies(API_KEY)
            .flatMap { Flowable.fromIterable(it.result) }
            .doOnNext { movie -> insertMovie(movie) }

    }

    private fun insertMovie(movie: Movie) {
        movieDao.insertMovie(
            MovieEntity(
                idMovie = movie.idMovie!!,
                title = movie.title,
                originalTitle = movie.originalTitle,
                releaseDate = movie.releaseDate,
                overview = movie.overview,
                posterPath = movie.posterPath,
                voteAverage = movie.voteAverage
            )
        )
    }

    fun getMovieFromLocal(): Flowable<List<MovieEntity>> {
        return movieDao.getMovie()
    }
}