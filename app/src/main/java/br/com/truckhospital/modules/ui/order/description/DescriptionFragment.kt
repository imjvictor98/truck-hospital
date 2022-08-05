package br.com.truckhospital.modules.ui.order.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import br.com.truckhospital.databinding.FragmentDescriptionBinding
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.order.OrderPageEnum
import br.com.truckhospital.modules.ui.order.flows.create.CreateOrderActivity
import br.com.truckhospital.modules.ui.order.vehicle.VehicleFragment
import br.com.truckhospital.modules.util.PairUtil.pairOf
import timber.log.Timber

class DescriptionFragment constructor(private val type: OrderPageEnum):
    BaseFragment<DescriptionContract.Presenter>(), DescriptionContract.View {

    companion object {
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        const val REQUEST_EXTRA_DESCRIPTION = "REQUEST_EXTRA_DESCRIPTION"
        const val EXTRA_SERVICE = "EXTRA_SERVICE"
        const val REQUEST_EXTRA_SERVICE = "REQUEST_EXTRA_SERVICE"
    }

    private var binding: FragmentDescriptionBinding? = null
    private var activity: CreateOrderActivity? = null
    private var mOrder: Order? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)
        setPresenter(DescriptionPresenter(this))
        activity = requireActivity() as? CreateOrderActivity
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (type == OrderPageEnum.ORDER_PAGE_COMPLAIN) {
            setFragmentResultListener(VehicleFragment.REQUEST_EXTRA_VEHICLE) { _, bundle ->
                mOrder = bundle.getSerializable(VehicleFragment.EXTRA_VEHICLE) as? Order
                Timber.d("Test SFR (Description) coming from vehicle: %s", mOrder?.vehicle?.model)
            }
        } else {
            setFragmentResultListener(REQUEST_EXTRA_DESCRIPTION) { _, bundle ->
                mOrder = bundle.getSerializable(EXTRA_DESCRIPTION) as? Order
                Timber.d("Test SFR (Service) coming from complaint: %s", mOrder?.complaint?.description)
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fragmentEditTil?.hint = when (type) {
            OrderPageEnum.ORDER_PAGE_COMPLAIN -> "Tem alguma reclamação?"
            else -> "Diz aí pra gente o que você fez no serviço"
        }
        binding?.fragmentDescriptionFab?.setOnClickListener {
            getPresenter()?.getDescription(binding?.fragmentDescriptionEdit?.text.toString())?.let {
                if (type == OrderPageEnum.ORDER_PAGE_COMPLAIN) {
                    setFragmentResult(REQUEST_EXTRA_DESCRIPTION, bundleOf(pairOf(EXTRA_DESCRIPTION, mOrder?.copy(complaint = it))))
                } else if (type == OrderPageEnum.ORDER_PAGE_SERVICE) {
                    setFragmentResult(REQUEST_EXTRA_SERVICE, bundleOf(pairOf(EXTRA_SERVICE, mOrder?.copy(service = it))))
                }
                activity?.goForward()
            }
        }
    }
}

