package br.com.truckhospital.modules.ui.order.flows.create

import br.com.truckhospital.modules.core.database.RealTimeDataBase
import br.com.truckhospital.modules.core.repository.OrderRepositoryImpl
import br.com.truckhospital.modules.core.model.Order

class CreateOrderPresenter(override val view: CreateOrderContract.View?) : CreateOrderContract.Presenter {

    private val database by lazy {
        OrderRepositoryImpl(RealTimeDataBase.dataBase.reference)
    }

    override fun createOrder(order: Order) {
        database.addOrder(order, onSuccess = {
            view?.showSuccess(order.orderId.toString())
        })
    }
}