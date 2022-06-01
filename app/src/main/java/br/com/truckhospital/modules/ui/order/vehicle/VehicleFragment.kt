package br.com.truckhospital.modules.ui.order.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import br.com.truckhospital.databinding.FragmentVehicleBinding
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.order.OrderActivity

class VehicleFragment: BaseFragment<VehiclePresenter>(), VehicleContract.View {

    private var binding: FragmentVehicleBinding? = null
    private var activity: OrderActivity? = null

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
                activity?.setVehicle(it)
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