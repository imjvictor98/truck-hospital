package br.com.truckhospital.modules.ui.order.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.truckhospital.databinding.FragmentBudgetBinding
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.order.OrderActivity

class BudgetFragment : BaseFragment<BudgetContract.Presenter>(), BudgetContract.View {
    private var binding: FragmentBudgetBinding? = null
    private var activity: OrderActivity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBudgetBinding.inflate(layoutInflater, container, false)
        setPresenter(BudgetPresenter(this))
        activity = requireActivity() as? OrderActivity
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}