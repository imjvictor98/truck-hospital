package br.com.truckhospital.modules.ui.home.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.truckhospital.databinding.ItemMainActionBinding

class MainActionListAdapter(
    private val list: List<Pair<String, Int>>,
    private val mListener: MainActionMenuListener? = null
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface MainActionMenuListener {
        fun onClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemMainActionBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.itemView.apply {
                holder.icon.setImageResource(list[position].second)
                holder.title.text = list[position].first
                setOnClickListener {
                    mListener?.onClick()
                }
            }
        }
    }

    override fun getItemCount() = list.size

    private inner class ViewHolder(view: ItemMainActionBinding): RecyclerView.ViewHolder(view.root) {
        val icon = view.itemMainActionIcon
        val title = view.itemMainActionTitle
    }
}