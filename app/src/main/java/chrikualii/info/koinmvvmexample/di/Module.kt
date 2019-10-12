package chrikualii.info.koinmvvmexample.di

import android.arch.persistence.room.Room
import chrikualii.info.koinmvvmexample.BaseApp.Companion.BASE_URL
import chrikualii.info.koinmvvmexample.data.db.MovieDb
import chrikualii.info.koinmvvmexample.data.network.ApiService
import chrikualii.info.koinmvvmexample.data.repo.MovieRepo
import chrikualii.info.koinmvvmexample.ui.MainViewModel
import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by chirikualii on 09/12/18
 * github.com/chirikualii
 */

val appModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(get()) }

    //repo
    single { MovieRepo(get(), get()) }

    //viewmodel
    viewModel { MainViewModel(get()) }
}

val serviceModule = module {
    single { createApiService(get()) }
}

val dataModule = module {
    val ctx by lazy { androidApplication() }
    single { Room.databaseBuilder(ctx, MovieDb::class.java, "movie.db").build() }
    single { get<MovieDb>().movieDao() }
}

fun createOkHttpClient(): OkHttpClient {

    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(okHttpClient)
        .build()
}

fun createApiService(retrofit: Retrofit): ApiService = retrofit.create(
    ApiService::class.java
)