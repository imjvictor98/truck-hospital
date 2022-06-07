package br.com.truckhospital.modules.ui.order.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import br.com.truckhospital.databinding.FragmentBudgetBinding
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.order.OrderActivity
import br.com.truckhospital.modules.util.extension.addDecimalInputFilter

class BudgetFragment : BaseFragment<BudgetContract.Presenter>(), BudgetContract.View {
    private var binding: FragmentBudgetBinding? = null
    private var activity: OrderActivity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBudgetBinding.inflate(layoutInflater, container, false)
        setPresenter(BudgetPresenter(this))
        activity = requireActivity() as? OrderActivity
        return binding?.root
    }

    override fun onResume() {
        setNextButton(getPresenter()?.isAllFieldsFilled() == true)
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fragmentBudgetLaborCost?.apply {
            addDecimalInputFilter(2)
            addTextChangedListener {
                getPresenter()?.formatCosts(this.text.toString(),  binding?.fragmentBudgetPartsCost?.text.toString())
                getPresenter()?.setValidLaborCost(it.toString())
            }
        }

        binding?.fragmentBudgetPartsCost?.apply {
            addDecimalInputFilter(2)
            addTextChangedListener {
                getPresenter()?.formatCosts(binding?.fragmentBudgetLaborCost?.text.toString(), this.text.toString())
                getPresenter()?.setValidLaborCost(binding?.fragmentBudgetLaborCost?.text.toString())
            }
        }

        binding?.fragmentBudgetFab?.setOnClickListener {
            getPresenter()?.getBudget(
                binding?.fragmentBudgetLaborCost?.text.toString(),
                binding?.fragmentBudgetPartsCost?.text.toString()
            )?.let {
                activity?.setBudget(it)
                activity?.goForward()
            }
        }
    }

    override fun setNextButton(value: Boolean) {
        binding?.fragmentBudgetFab?.isEnabled = value
    }

    override fun setTotalCost(total: String) {
        binding?.fragmentBudgetValueCost?.text = total
    }

    override fun setErrorLaborCost(errorText: String) {
        binding?.fragmentBudgetLaborCost?.error = errorText
    }
}