package br.com.truckhospital.modules.ui.order

import br.com.truckhospital.modules.core.model.*

class OrderPresenter(override val view: OrderContract.View?) : OrderContract.Presenter {

    private var mOrder: Order? = null

    override fun setClient(client: Client) {
        mOrder = mOrder?.copy(client = client)
    }

    override fun setVehicle(vehicle: Vehicle) {
        mOrder = mOrder?.copy(vehicle = vehicle)
    }

    override fun setComplain(complaint: Complaint) {
        mOrder = mOrder?.copy(complaint = complaint)
    }

    override fun setService(complaint: Complaint) {
        mOrder = mOrder?.copy(service = complaint)
    }

    override fun setBudget(budget: Budget) {
        mOrder = mOrder?.copy(budget = budget)
    }

}