package br.com.truckhospital.modules.ui.order.create

class CreateOrderBase {
    interface View {
        fun setNextButton(value: Boolean)
    }

    interface Presenter {
        fun isAllFieldsFilled(): Boolean
    }
}