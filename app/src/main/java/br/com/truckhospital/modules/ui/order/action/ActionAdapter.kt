package br.com.truckhospital.modules.ui.order.action

import android.content.Context
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ItemListActionBinding
import br.com.truckhospital.modules.core.model.ActionType
import br.com.truckhospital.modules.core.model.toIconDTO

class ActionAdapter(
    private val mValues: List<ActionType>,
    private val mListener: ActionListener? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ActionListener {
        fun onClick(type: ActionType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemListActionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ActionAdapter.ViewHolder)?.apply {
            val item = mValues[position]

            when (item) {
                ActionType.WHATSAPP -> {
                    val color = ContextCompat.getColor(itemView.context, R.color.green_300)
                    icon.setColorFilter(color)
                    text.setTextColor(color)
                }
                else -> {
                    val color = ContextCompat.getColor(itemView.context, R.color.primary)
                    icon.setColorFilter(color)
                    text.setTextColor(color)
                }
            }
            text.text = item.text
            icon.setImageResource(item.toIconDTO().icon)
            container.setOnClickListener {
                mListener?.onClick(item)
            }
        }
    }

    override fun getItemCount() = mValues.size

    inner class ViewHolder(view: ItemListActionBinding): RecyclerView.ViewHolder(view.root) {
        val container = view.itemListActionContainer
        val icon = view.itemListActionIcon
        val text = view.itemListActionText
    }
}