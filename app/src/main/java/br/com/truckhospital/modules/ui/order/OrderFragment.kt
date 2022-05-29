package br.com.truckhospital.modules.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.truckhospital.R
import br.com.truckhospital.databinding.FragmentOrderBinding

class OrderFragment(page: OrderActivity.OrderPageEnum) : Fragment() {

    companion object {
        fun getInstance(page: OrderActivity.OrderPageEnum): OrderFragment {
            return OrderFragment(page)
        }
    }

    private var binding: FragmentOrderBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }
}