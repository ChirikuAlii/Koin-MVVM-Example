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
}