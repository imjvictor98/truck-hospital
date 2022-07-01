package br.com.truckhospital.modules.core.repository

import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.util.FirebaseAuthHelper
import br.com.truckhospital.modules.util.PairUtil.pairOf
import com.google.firebase.database.DatabaseReference

class OrderRepositoryImpl(
    private val database: DatabaseReference
) : OrderRepositoryContract {
    companion object {
        private const val ORDERS = "orders"
    }

    override fun addOrder(order: Order, onSuccess: () -> Unit) {
        FirebaseAuthHelper.userAuth.currentUser?.let { firebaseUser ->
            database
                .child(firebaseUser.uid)
                .child(ORDERS)
                .setValue(mapOf(pairOf(order.orderId, order)))
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
}