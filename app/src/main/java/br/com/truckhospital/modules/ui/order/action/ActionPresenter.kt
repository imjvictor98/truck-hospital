package br.com.truckhospital.modules.ui.order.action

import br.com.truckhospital.modules.ui.order.OrderPage

class ActionPresenter(override val view: ActionContract.View?) : ActionContract.Presenter {

    override fun start() {
        val actions = OrderPage.actionsForShow
        view?.setActionList(actions)
    }
}