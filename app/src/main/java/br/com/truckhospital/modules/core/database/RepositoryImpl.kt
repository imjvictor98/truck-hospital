package br.com.truckhospital.modules.core.database

import br.com.truckhospital.modules.core.model.Order
import com.google.firebase.database.DatabaseReference

class RepositoryImpl(
    private val database: DatabaseReference
): RepositoryContract {
    companion object {
        private const val ORDERS = "orders"
    }
    override fun addOrder(order: Order) {
        database
            .child(ORDERS)
            .setValue(order)
    }

    override fun updateOrder(orderId: String) {

    }

    override fun deleteOrder(orderId: String) {

    }

    override fun getOrders(): List<Order> {
        return emptyList()
    }
}