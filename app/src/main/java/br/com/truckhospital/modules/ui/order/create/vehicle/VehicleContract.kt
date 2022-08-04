package br.com.truckhospital.modules.ui.order.create.vehicle

import br.com.truckhospital.modules.core.model.Vehicle
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract
import br.com.truckhospital.modules.ui.order.create.CreateOrderBase

class VehicleContract {
    interface View: BaseFragmentContract.BaseView,  CreateOrderBase.View {
        fun setErrorPlate(errorText: String)

        fun setErrorBrand(errorText: String)

        fun setErrorModel(errorText: String)
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View>,  CreateOrderBase.Presenter {
        fun setValidPlate(text: String)

        fun setValidBrand(text: String)

        fun setValidModel(text: String)

        fun getVehicle(plate: String, brand: String, model: String): Vehicle
    }
}