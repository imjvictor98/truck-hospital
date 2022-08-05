package br.com.truckhospital.modules.ui.order.flows.create

class CreateOrderBase {
    interface View {
        fun setNextButton(value: Boolean)
    }

    interface Presenter {
        fun isAllFieldsFilled(): Boolean
    }
}