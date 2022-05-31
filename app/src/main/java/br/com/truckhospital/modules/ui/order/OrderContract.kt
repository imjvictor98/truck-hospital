package br.com.truckhospital.modules.ui.order

import br.com.truckhospital.modules.core.model.Client
import br.com.truckhospital.modules.core.model.Complaint
import br.com.truckhospital.modules.core.model.Vehicle
import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

class OrderContract: BaseActivityContract {
    interface View : BaseActivityContract.BaseView {
        fun showDialog()
    }

    interface Presenter : BaseActivityContract.BasePresenter<View>{
        fun setClient(client: Client)
        fun setVehicle(vehicle: Vehicle)
        fun setComplain(complaint: Complaint)
    }
}