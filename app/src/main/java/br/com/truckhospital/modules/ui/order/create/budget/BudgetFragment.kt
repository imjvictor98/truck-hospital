package br.com.truckhospital.modules.ui.order.create.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResultListener
import br.com.truckhospital.databinding.FragmentBudgetBinding
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.order.create.CreateOrderActivity
import br.com.truckhospital.modules.ui.order.create.description.DescriptionFragment
import br.com.truckhospital.modules.util.extension.addDecimalInputFilter
import br.com.truckhospital.modules.util.extension.gone
import br.com.truckhospital.modules.util.extension.invisible
import br.com.truckhospital.modules.util.extension.visible
import timber.log.Timber
import java.util.*

class BudgetFragment() : BaseFragment<BudgetContract.Presenter>(), BudgetContract.View {
    private var binding: FragmentBudgetBinding? = null
    private var activity: CreateOrderActivity? = null
    private var mOrder: Order? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBudgetBinding.inflate(layoutInflater, container, false)
        setPresenter(BudgetPresenter(this))
        activity = requireActivity() as? CreateOrderActivity
        return binding?.root
    }

    override fun onResume() {
        setNextButton(getPresenter()?.isAllFieldsFilled() == true)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(DescriptionFragment.REQUEST_EXTRA_SERVICE) { _, bundle ->
            mOrder = bundle.getSerializable(DescriptionFragment.EXTRA_SERVICE) as? Order
            Timber.d("Test SFR (Budget) coming from service: %s", mOrder?.service?.description)
        }
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
                if (mOrder != null) {
                    activity?.finishForm(mOrder!!.copy(orderId = UUID.randomUUID().toString(), budget = it))
                }
//              hideDoneButton()
//              showLoading()
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

    override fun showLoading() {
        binding?.fragmentBudgetFabLoading?.visible()
    }

    override fun hideLoading() {
        binding?.fragmentBudgetFabLoading?.gone()
    }

    override fun showDoneButton() {
        binding?.fragmentBudgetFab?.visible()
    }

    override fun hideDoneButton() {
        binding?.fragmentBudgetFab?.invisible()
    }
}