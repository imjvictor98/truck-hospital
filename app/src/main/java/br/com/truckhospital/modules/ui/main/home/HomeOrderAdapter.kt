package br.com.truckhospital.modules.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.truckhospital.databinding.ItemListOrderBinding
import br.com.truckhospital.modules.MainApplication
import br.com.truckhospital.modules.core.model.Order
import java.text.NumberFormat

class HomeOrderAdapter(private val mValues: List<Order>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(itemView: ItemListOrderBinding): RecyclerView.ViewHolder(itemView.root) {
        val title: TextView = itemView.itemListOrderTitle
        val description: TextView = itemView.itemListOrderDescription
        val total: TextView = itemView.itemListOrderTotal
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
            NumberFormat.getCurrencyInstance(MainApplication.localeBRL).apply {
                maximumFractionDigits = 2
                total.text = this.format(order.budget?.totalCost)
            }
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }
}