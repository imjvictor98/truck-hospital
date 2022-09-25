package br.com.truckhospital.modules.ui.order.action

import br.com.truckhospital.modules.core.model.ActionType
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract

interface ActionContract {

    interface View: BaseFragmentContract.BaseView {
        fun setActionList(actions: List<ActionType>)
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View> {
        fun start()
    }
}