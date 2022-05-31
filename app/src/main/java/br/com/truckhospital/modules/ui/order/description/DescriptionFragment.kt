package br.com.truckhospital.modules.ui.order.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.truckhospital.databinding.FragmentDescriptionBinding
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment

class DescriptionFragment: BaseFragment<DescriptionContract.Presenter>(), DescriptionContract.View {

    private var binding: FragmentDescriptionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}

