package chrikualii.info.koinmvvmexample.util.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by chirikualii on 11/12/18
 * github.com/chirikualii
 */
abstract class RxViewModel : ViewModel() {

    protected abstract val TAG: String
    private val disposable = CompositeDisposable()
    protected val _states = MutableLiveData<ViewModelState>()
    private val loading = Loading(false)
    private var failed = Failed()
    private var success = Success()
    val states: LiveData<ViewModelState>
        get() = _states

    fun launch(job: () -> Disposable) {
        disposable.add(job())

    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun showLoading(isLoading: Boolean){
        loading.isLoading = isLoading
        _states.value = loading
    }

    fun showErrorMessage(message: String?=null,t: Throwable?=null){
        if(message!= null){
            failed.error = message
            _states.value = failed
        }

        if (t !=null){
            when (t){

                is java.net.UnknownHostException -> {
                    failed.error = "No Internet Connection"
                    _states.value = failed
                }

                else ->{
                    failed.error = t.message
                    _states.value = failed
                }
            }
        }

    }

    fun showSuccessMessage(message:String?=null){
        success.message = message
        _states.value = success
    }
}