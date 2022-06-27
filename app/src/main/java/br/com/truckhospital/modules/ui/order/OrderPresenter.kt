package br.com.truckhospital.modules.ui.order

import br.com.truckhospital.modules.core.database.RealTimeDataBase
import br.com.truckhospital.modules.core.repository.OrderRepositoryImpl
import br.com.truckhospital.modules.core.model.*

class OrderPresenter(override val view: OrderContract.View?) : OrderContract.Presenter {

    private val database by lazy {
        OrderRepositoryImpl(RealTimeDataBase.dataBase.reference)
    }

    override fun createOrder(order: Order) {
        database.addOrder(order, onSuccess = {
            view?.showSuccess(order.orderId.toString())
        })
    }
}