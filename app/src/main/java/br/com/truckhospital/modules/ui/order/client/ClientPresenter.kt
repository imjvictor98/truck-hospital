package br.com.truckhospital.modules.ui.order.client

import br.com.truckhospital.modules.core.model.Client
import br.com.truckhospital.modules.util.CNPJUtil


class ClientPresenter(override val view: ClientContract.View?) : ClientContract.Presenter {

    private var mValidCNPJ = false
    private var mValidCEP = false
    private var mValidName = false
    private var mValidPhoneNumber = false

    override fun isAllFieldsFilled(): Boolean {
        return mValidCNPJ && mValidCEP && mValidName && mValidPhoneNumber
    }

    override fun setValidCNPJ(text: String) {
        mValidCNPJ = CNPJUtil.isValid(text)
        if (mValidCNPJ) {
            view?.setNextButton(isAllFieldsFilled())
        } else {
            view?.setErrorCNPJ("CNPJ inválido")
        }
    }

    override fun setValidCEP(text: String) {
        mValidCEP = text.length >= 8
        if (mValidCEP) {
            view?.setNextButton(isAllFieldsFilled())
        } else {
            view?.setErrorCEP("CEP inválido")
        }
    }

    override fun setValidName(text: String) {
        mValidName = text.length >= 4
        if (mValidName) {
            view?.setNextButton(isAllFieldsFilled())
        } else {
            view?.setErrorName("Nome inválido")
        }
    }

    override fun setValidPhoneNumber(text: String) {
        mValidPhoneNumber = text.length >= 11
        if (mValidPhoneNumber) {
            view?.setNextButton(isAllFieldsFilled())
        } else {
            view?.setErrorPhoneNumber("Número inválido")
        }
    }

    override fun getClient(cpf: String, cep: String, name: String, number: String) = Client(cpf, name, number, cep)

    override fun checkMode(isReadMode: Boolean, client: Client?) {
        if (isReadMode && client != null) {
            view?.applyReadMode()
        } else {
            view?.applyEditMode()
        }
    }
}