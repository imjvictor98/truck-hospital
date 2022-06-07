package br.com.truckhospital.modules.ui.order.budget

import br.com.truckhospital.modules.MainApplication
import br.com.truckhospital.modules.core.model.Budget
import java.text.NumberFormat

class BudgetPresenter(override val view: BudgetContract.View?) : BudgetContract.Presenter {

    private var mIsValid = false

    override fun isAllFieldsFilled(): Boolean {
        return mIsValid
    }

    override fun setValidLaborCost(text: String) {
        val textWithoutComma = text.replace(",", "")
        mIsValid =  textWithoutComma.length >= 3 && textWithoutComma != "000"
        if (mIsValid) {
            view?.setNextButton(isAllFieldsFilled())
        } else {
            view?.setErrorLaborCost("Valor deve ser maior do que zero")
        }
    }

    override fun formatCosts(labor: String, parts: String) {
        val laborCost = labor
            .replace(",", ".")
            .toDoubleOrNull()

        val partsCost = parts
            .replace(",", ".")
            .toDoubleOrNull() ?: 0.00

        if (laborCost != null) {
            val numberFormat = NumberFormat.getCurrencyInstance(MainApplication.localeBRL).apply {
                maximumFractionDigits = 2
            }
            val totalCost = laborCost + partsCost
            view?.setTotalCost(numberFormat.format(totalCost))
        } else {
            view?.setTotalCost("")
        }
    }

    override fun getBudget(laborCost: String, partsCost: String): Budget {
        return Budget(0F, 0F, 0F)
    }

}