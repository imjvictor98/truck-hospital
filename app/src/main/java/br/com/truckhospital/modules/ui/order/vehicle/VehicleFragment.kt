package br.com.truckhospital.modules.ui.order.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.truckhospital.databinding.FragmentVehicleBinding
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment

class VehicleFragment: BaseFragment<VehiclePresenter>(), VehicleContract.View {

    private var binding: FragmentVehicleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVehicleBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}