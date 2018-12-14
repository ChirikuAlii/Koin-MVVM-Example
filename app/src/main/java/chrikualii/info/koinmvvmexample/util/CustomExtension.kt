package chrikualii.info.koinmvvmexample.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import chrikualii.info.koinmvvmexample.BuildConfig
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.JsonElement

/**
 * Created by chirikualii on 10/12/18
 * github.com/chirikualii
 */

fun toJsonElement(any: Any): JsonElement = Gson().toJsonTree(any)

fun Throwable.checkError(TAG: String): String? {
    var msg: String? = null
    when (this) {
        is java.net.UnknownHostException -> msg = "No Internet Connection"
        else -> {
            Log.e(TAG, this.localizedMessage)
        }
    }
    return msg

}

fun logI(tag: String, msg: String?) {
    if (BuildConfig.DEBUG) Log.i(tag, msg)
}

fun logW(tag: String, msg: String?) {
    if (BuildConfig.DEBUG) Log.w(tag, msg)
}

fun logE(tag: String, msg: String?) {
    if (BuildConfig.DEBUG) Log.e(tag, msg)
}

fun logD(tag: String, msg: String?) {
    if (BuildConfig.DEBUG) Log.d(tag, msg)
}

fun ImageView.loadImage(context: Context, url: String) {
    Glide
        .with(context)
        .load(url)
        .into(this)
}
