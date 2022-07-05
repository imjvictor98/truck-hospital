package br.com.truckhospital.modules.core.repository

import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.util.FirebaseAuthHelper
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class OrderRepositoryImpl(
    private val database: DatabaseReference,
    private val eventListener: ValueEventListener? = null
) : OrderRepositoryContract {
    companion object {
        const val ORDERS = "orders"
    }

    override fun addOrder(order: Order, onSuccess: () -> Unit) {
        FirebaseAuthHelper.getUserId()?.let { uid ->
            database
                .child(uid)
                .child(ORDERS)
                .push()
                .setValue(order)
                .addOnSuccessListener { onSuccess() }
        }
    }

    override fun updateOrder(orderId: String) {

    }

    override fun deleteOrder(orderId: String) {

    }

    override fun getOrders(): List<Order> {
        return emptyList()
    }

    override fun registerValueEventListener() {
        eventListener?.run {
            database.addValueEventListener(this@run)
        }
    }
}