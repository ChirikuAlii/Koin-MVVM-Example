package chrikualii.info.koinmvvmexample.util.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by chirikualii on 13/12/18
 * github.com/chirikualii
 */
class GlobalViewHolder<T>(val view: View) : RecyclerView.ViewHolder(view) {
    var binding: ViewDataBinding? = DataBindingUtil.bind(view)

    fun onBind(variable: Int, value: T) {
        if (value == null) return;
        binding?.setVariable(variable, value);
        binding?.executePendingBindings();
    }
}