package br.com.truckhospital.modules.ui.main.home

import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract

class HomeContract {
    interface View: BaseFragmentContract.BaseView {
        fun goToSplash()

        fun setDescription(text: String)

        fun setOrdersList(orders: List<Order>)

        fun startSkeletonOrderList()

        fun stopSkeletonOrderList()
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View> {
        fun getPhoneNumber()

        fun signOut()

        fun createListenerForOrderList()
    }
}