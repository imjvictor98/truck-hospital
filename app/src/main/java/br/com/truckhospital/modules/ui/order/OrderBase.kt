package br.com.truckhospital.modules.ui.order

class OrderBase {
    interface View {
        fun setNextButton(value: Boolean)
    }

    interface Presenter {
        fun isAllFieldsFilled(): Boolean
    }
}