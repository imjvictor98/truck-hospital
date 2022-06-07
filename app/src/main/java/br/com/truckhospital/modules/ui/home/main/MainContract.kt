package br.com.truckhospital.modules.ui.home.main

import br.com.truckhospital.modules.ui.base.activity.BaseActivityContract

class MainContract {
    interface View: BaseActivityContract.BaseView {
        fun goToOrder()

        fun goToSplash()

        fun setMenuListAdapter(list: List<Pair<String, Int>>)
    }

    interface Presenter: BaseActivityContract.BasePresenter<View> {
        fun onCardClicked()

        fun signOut()

        fun createList()
    }
}