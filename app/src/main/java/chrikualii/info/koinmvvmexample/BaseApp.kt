package chrikualii.info.koinmvvmexample

import android.app.Application
import chrikualii.info.koinmvvmexample.di.appModule
import chrikualii.info.koinmvvmexample.di.dataModule
import chrikualii.info.koinmvvmexample.di.serviceModule
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger

/**
 * Created by chirikualii on 09/12/18
 * github.com/chirikualii
 */
class BaseApp : Application() {

    companion object {
        val BASE_URL = "https://api.themoviedb.org/3/"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, serviceModule, dataModule), logger = AndroidLogger(showDebug = true))
    }
}