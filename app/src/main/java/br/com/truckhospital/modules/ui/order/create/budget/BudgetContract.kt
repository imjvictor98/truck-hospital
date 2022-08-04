package br.com.truckhospital.modules.ui.order.create.budget

import br.com.truckhospital.modules.core.model.Budget
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract
import br.com.truckhospital.modules.ui.order.create.CreateOrderBase

class BudgetContract {
    interface View: BaseFragmentContract.BaseView, CreateOrderBase.View {
        fun setTotalCost(total: String)

        fun setErrorLaborCost(errorText: String)

        fun showLoading()

        fun hideLoading()

        fun showDoneButton()

        fun hideDoneButton()
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View>, CreateOrderBase.Presenter {
        fun setValidLaborCost(text: String)

        fun formatCosts(labor: String, parts: String)

        fun getBudget(labor: String, parts: String): Budget
    }
}