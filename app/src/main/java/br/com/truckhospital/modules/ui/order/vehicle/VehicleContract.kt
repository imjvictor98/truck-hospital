package br.com.truckhospital.modules.ui.order.vehicle

import br.com.truckhospital.modules.core.model.Vehicle
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract
import br.com.truckhospital.modules.ui.order.flows.create.CreateOrderBase

class VehicleContract {
    interface View: BaseFragmentContract.BaseView,  CreateOrderBase.View {
        fun setErrorPlate(errorText: String)

        fun setErrorBrand(errorText: String)

        fun setErrorModel(errorText: String)

        fun applyReadMode()

        fun applyEditMode()
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View>,  CreateOrderBase.Presenter {
        fun setValidPlate(text: String)

        fun setValidBrand(text: String)

        fun setValidModel(text: String)

        fun getVehicle(plate: String, brand: String, model: String): Vehicle

        fun checkMode(isReadMode: Boolean, vehicle: Vehicle?)
    }
}