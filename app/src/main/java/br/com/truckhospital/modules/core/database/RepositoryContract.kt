package br.com.truckhospital.modules.core.database

import br.com.truckhospital.modules.core.model.Order

interface RepositoryContract {
    fun addOrder(order: Order)
    fun updateOrder(orderId: String)
    fun deleteOrder(orderId: String)
    fun getOrders() : List<Order>
}