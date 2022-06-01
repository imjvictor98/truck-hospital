package br.com.truckhospital.modules.ui.order.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.truckhospital.databinding.FragmentDescriptionBinding
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.order.OrderActivity

class DescriptionFragment constructor(val type: OrderActivity.OrderPageEnum):
    BaseFragment<DescriptionContract.Presenter>(), DescriptionContract.View {

    private var binding: FragmentDescriptionBinding? = null
    private var activity: OrderActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)
        setPresenter(DescriptionPresenter(this))
        activity = requireActivity() as? OrderActivity
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fragmentEditTil?.hint = when (type) {
            OrderActivity.OrderPageEnum.ORDER_PAGE_COMPLAIN -> "Tem alguma reclamação?"
            else -> "Diz aí pra gente o que você fez no serviço"
        }
        binding?.fragmentDescriptionFab?.setOnClickListener {
            getPresenter()?.getDescription(binding?.fragmentDescriptionEdit?.text.toString())?.let {
                if (type == OrderActivity.OrderPageEnum.ORDER_PAGE_COMPLAIN) {
                    activity?.setDescription(it)
                } else if (type == OrderActivity.OrderPageEnum.ORDER_PAGE_SERVICE) {
                    activity?.setService(it)
                }
                activity?.goForward()
            }
        }
    }
}

