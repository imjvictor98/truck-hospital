package br.com.truckhospital.modules.ui.order.budget

import br.com.truckhospital.modules.core.model.Budget
import br.com.truckhospital.modules.ui.base.fragment.BaseFragmentContract
import br.com.truckhospital.modules.ui.order.OrderBase

class BudgetContract {
    interface View: BaseFragmentContract.BaseView, OrderBase.View {
        fun setTotalCost(total: String)

        fun setErrorLaborCost(errorText: String)
    }

    interface Presenter: BaseFragmentContract.BasePresenter<View>, OrderBase.Presenter {
        fun setValidLaborCost(text: String)

        fun formatCosts(labor: String, parts: String)

        fun getBudget(laborCost: String, partsCost: String): Budget
    }
}