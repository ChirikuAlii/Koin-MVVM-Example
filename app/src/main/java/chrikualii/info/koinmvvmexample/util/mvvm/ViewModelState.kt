package chrikualii.info.koinmvvmexample.util.mvvm

/**
 * Created by chirikualii on 11/12/18
 * github.com/chirikualii
 */
open class ViewModelState

data class Loading(val isLoading: Boolean) : ViewModelState()
data class Failed(val error: String?) : ViewModelState()