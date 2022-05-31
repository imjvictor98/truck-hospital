package br.com.truckhospital.modules.ui.order.client

import br.com.truckhospital.modules.core.model.Client
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract

class ClientContract {
    interface View: BaseFragmentContract.BaseView {
        fun setNextButton(value: Boolean)
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View> {
        fun isAllFieldsFilled(): Boolean

        fun setValidCNPJ(text: String)
        fun setValidCEP(text: String)
        fun setValidName(text: String)
        fun setValidPhoneNumber(text: String)
        fun getClient(cpf: String, cep: String, name: String, number: String): Client
    }
}