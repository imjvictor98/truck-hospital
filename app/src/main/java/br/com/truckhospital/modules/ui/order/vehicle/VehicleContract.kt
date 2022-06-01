package br.com.truckhospital.modules.ui.order.vehicle

import br.com.truckhospital.modules.core.model.Vehicle
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract

class VehicleContract {
    interface View: BaseFragmentContract.BaseView {
        fun setNextButton(value: Boolean)

        fun setErrorPlate(errorText: String)
        fun setErrorBrand(errorText: String)
        fun setErrorModel(errorText: String)
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View> {
        fun isAllFieldsFilled(): Boolean

        fun setValidPlate(text: String)
        fun setValidBrand(text: String)
        fun setValidModel(text: String)
        fun getVehicle(plate: String, brand: String, model: String): Vehicle
    }
}