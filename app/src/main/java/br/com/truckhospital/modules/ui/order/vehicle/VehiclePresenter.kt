package br.com.truckhospital.modules.ui.order.vehicle

import br.com.truckhospital.modules.core.model.Vehicle

class VehiclePresenter(override val view: VehicleContract.View?) : VehicleContract.Presenter {
    private var mValidPlate = false
    private var mValidBrand = false
    private var mValidModel = false

    override fun isAllFieldsFilled(): Boolean {
        return mValidPlate && mValidBrand && mValidModel
    }

    override fun setValidPlate(text: String) {
        mValidPlate = text.length >= 6
        if (mValidPlate) {
            view?.setNextButton(isAllFieldsFilled())
        } else {
            view?.setErrorPlate("Placa inválida")
        }
    }

    override fun setValidBrand(text: String) {
        mValidBrand = text.length >= 2
        if (mValidBrand) {
            view?.setNextButton(isAllFieldsFilled())
        } else {
            view?.setErrorBrand("Marca inválida")
        }
    }

    override fun setValidModel(text: String) {
        mValidModel = text.length >= 2
        if (mValidModel) {
            view?.setNextButton(isAllFieldsFilled())
        } else {
            view?.setErrorPlate("Modelo inválido")
        }
    }

    override fun getVehicle(plate: String, brand: String, model: String) = Vehicle(plate, brand, model)

}