package chrikualii.info.koinmvvmexample.ui

import chrikualii.info.koinmvvmexample.data.db.MovieEntity
import chrikualii.info.koinmvvmexample.data.repo.MovieRepo
import chrikualii.info.koinmvvmexample.util.checkError
import chrikualii.info.koinmvvmexample.util.logD
import chrikualii.info.koinmvvmexample.util.logE
import chrikualii.info.koinmvvmexample.util.mvvm.Failed
import chrikualii.info.koinmvvmexample.util.mvvm.Loading
import chrikualii.info.koinmvvmexample.util.mvvm.RxViewModel
import chrikualii.info.koinmvvmexample.util.mvvm.ViewModelState
import chrikualii.info.koinmvvmexample.util.toJsonElement
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by chirikualii on 10/12/18
 * github.com/chirikualii
 */

class MainViewModel(private val repo: MovieRepo) : RxViewModel() {
    override val TAG: String = MainViewModel::class.java.simpleName

    data class MovieLoaded(val movieList: List<MovieEntity>? = null) : ViewModelState()

    fun loadMovie() {
        loadMovieLocal()
        _states.value = Loading(true)
        launch {
            repo.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _states.value = Loading(false)
                }, {
                    _states.value = Loading(false)
                    _states.value = Failed(it.checkError(TAG))
                })
        }
    }

    private fun loadMovieLocal() {
        launch {
            repo.getMovieFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    logD(TAG, "data: ${toJsonElement(it)}")
                    _states.value = MovieLoaded(it)
                }, {
                    logE(TAG, it.message)
                })
        }
    }


}
