package br.com.truckhospital.modules.ui.order.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import br.com.truckhospital.databinding.FragmentVehicleBinding
import br.com.truckhospital.modules.core.model.Client
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.order.OrderActivity
import br.com.truckhospital.modules.ui.order.client.ClientFragment
import br.com.truckhospital.modules.util.pairOf
import timber.log.Timber

class VehicleFragment: BaseFragment<VehiclePresenter>(), VehicleContract.View {

    companion object {
        const val EXTRA_VEHICLE = "EXTRA_VEHICLE"
        const val REQUEST_EXTRA_VEHICLE = "REQUEST_EXTRA_VEHICLE"
    }

    private var binding: FragmentVehicleBinding? = null
    private var activity: OrderActivity? = null
    private var mOrder: Order? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVehicleBinding.inflate(layoutInflater, container, false)
        setPresenter(VehiclePresenter(this))
        activity = requireActivity() as? OrderActivity
        return binding?.root
    }

    override fun onResume() {
        setNextButton(getPresenter()?.isAllFieldsFilled() == true)
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(ClientFragment.REQUEST_EXTRA_CLIENT) { _, bundle ->
            mOrder = bundle.getSerializable(ClientFragment.EXTRA_CLIENT) as? Order
            Timber.d("Test SFR (Vehicle) coming from client: %s", mOrder?.client?.name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fragmentVehiclePlate?.addTextChangedListener {
            getPresenter()?.setValidPlate(it.toString())
        }

        binding?.fragmentVehicleBrand?.addTextChangedListener {
            getPresenter()?.setValidBrand(it.toString())
        }

        binding?.fragmentVehicleModel?.addTextChangedListener {
            getPresenter()?.setValidModel(it.toString())
        }

        binding?.fragmentVehicleFab?.setOnClickListener {
            getPresenter()?.getVehicle(
                binding?.fragmentVehiclePlate?.text.toString(),
                binding?.fragmentVehicleBrand?.text.toString(),
                binding?.fragmentVehicleModel?.text.toString()
            )?.let {
                setFragmentResult(REQUEST_EXTRA_VEHICLE, bundleOf(pairOf(EXTRA_VEHICLE, mOrder?.copy(vehicle = it))))
                activity?.goForward()
            }
        }
    }

    override fun setNextButton(value: Boolean) {
        binding?.fragmentVehicleFab?.isEnabled = value
    }

    override fun setErrorPlate(errorText: String) {
        binding?.fragmentVehiclePlate?.error = errorText
    }

    override fun setErrorBrand(errorText: String) {
        binding?.fragmentVehicleBrand?.error = errorText
    }

    override fun setErrorModel(errorText: String) {
        binding?.fragmentVehicleModel?.error = errorText
    }
}