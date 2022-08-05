package br.com.truckhospital.modules.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.truckhospital.databinding.ItemListOrderBinding
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.util.extension.NumberUtil
import com.google.android.material.card.MaterialCardView

class HomeOrderAdapter(
    private val mValues: List<Order>,
    private val mListener: HomeOrderListener? = null
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface HomeOrderListener {
        fun onClick(order: Order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemListOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.apply {
            val order = mValues[position]
            title.text = order.client?.name
            description.text = "04 Julho 2022"

            total.text = NumberUtil.getStringFormattedByCurrency(2, order.budget?.totalCost)

            container.setOnClickListener {
                mListener?.onClick(order)
            }
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(itemView: ItemListOrderBinding): RecyclerView.ViewHolder(itemView.root) {
        val title: TextView = itemView.itemListOrderTitle
        val description: TextView = itemView.itemListOrderDescription
        val total: TextView = itemView.itemListOrderTotal
        val container: MaterialCardView = itemView.itemListOrderContainer
    }
}