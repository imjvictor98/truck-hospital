package br.com.truckhospital.modules.core.repository

import br.com.truckhospital.modules.core.model.Order

interface OrderRepositoryContract {
    fun addOrder(order: Order, onSuccess: () -> Unit)
    fun updateOrder(orderId: String)
    fun deleteOrder(orderId: String)
    fun getOrders() : List<Order>
    fun registerValueEventListener()
}