package chrikualii.info.koinmvvmexample.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import chrikualii.info.koinmvvmexample.R
import chrikualii.info.koinmvvmexample.data.db.MovieEntity
import chrikualii.info.koinmvvmexample.util.mvvm.Failed
import chrikualii.info.koinmvvmexample.util.mvvm.Loading
import chrikualii.info.koinmvvmexample.util.view.GlobalAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupView()
        this.observeViewModel()
        mainViewModel.loadMovie()
    }

    private fun observeViewModel() {
        mainViewModel.states.observe(this, Observer { state ->
            when (state) {
                is MainViewModel.MovieLoaded -> {
                    recycler_view.adapter = object :
                        GlobalAdapter<MovieEntity>(layoutId = R.layout.item_movie, listData = state.movieList) {}
                }
                is Loading -> {
                    progress_circular.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                    recycler_view.visibility = if (state.isLoading) View.GONE else View.VISIBLE
                }
                is Failed -> {
                    if (state.error != null) toast(state.error)
                }
            }
        })
    }

    private fun setupView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
    }


}
