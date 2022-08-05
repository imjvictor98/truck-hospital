package br.com.truckhospital.modules.ui.order.client

import br.com.truckhospital.modules.core.model.Client
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract
import br.com.truckhospital.modules.ui.order.flows.create.CreateOrderBase

class ClientContract {
    interface View: BaseFragmentContract.BaseView, CreateOrderBase.View {
        fun setErrorCNPJ(errorText: String)

        fun setErrorCEP(errorText: String)

        fun setErrorName(errorText: String)

        fun setErrorPhoneNumber(errorText: String)

        fun applyReadMode()

        fun applyEditMode()
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View>,  CreateOrderBase.Presenter {
        fun setValidCNPJ(text: String)

        fun setValidCEP(text: String)

        fun setValidName(text: String)

        fun setValidPhoneNumber(text: String)

        fun getClient(cpf: String, cep: String, name: String, number: String): Client

        fun checkMode(isReadMode: Boolean, client: Client?)
    }
}