package chrikualii.info.koinmvvmexample.util.view

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chrikualii.info.koinmvvmexample.BR

/**
 * Created by chirikualii on 13/12/18
 * github.com/chirikualii
 */
abstract class GlobalAdapter<T>(
    @field:LayoutRes private var layoutId: Int,
    private val listData: List<T>? = null, private val viewNoData: View? = null
    , val onItemClick: OnItemClick? = null
) :
    RecyclerView.Adapter<GlobalViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobalViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return GlobalViewHolder(view)
    }

    override fun onBindViewHolder(holder: GlobalViewHolder<T>, position: Int) {
        holder.itemView.setOnClickListener { onItemClick?.itemClick(position) }
        listData?.apply { holder.onBind(BR.model, listData[position]) }
    }

    override fun getItemCount(): Int {
        viewNoData?.let { vNoData ->
            if (listData?.isEmpty() == true && vNoData.visibility == View.GONE) {
                View.VISIBLE
            } else if (listData?.isEmpty() == false && vNoData.visibility == View.VISIBLE) {
                viewNoData.visibility = View.GONE
            } else {
            }
        }
        return listData?.size ?: 0
    }

    fun changeLayout(layoutId: Int) {
        this.layoutId = layoutId
    }

    fun removeItem(viewholder: GlobalViewHolder<T>? = null) {
        if (viewholder != null) {
            if (listData is ArrayList && listData.isNotEmpty()) {
                listData.remove(listData[viewholder.layoutPosition])
                notifyItemRemoved(viewholder.layoutPosition)
            }
        } else {
            if (listData is ArrayList) {
                listData.clear()
                notifyDataSetChanged()
            }
        }
    }


    interface OnItemClick {
        fun itemClick(position: Int)
    }
}