package br.com.truckhospital.modules.ui.order.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.truckhospital.databinding.FragmentClientBinding
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.util.EDIT_TEXT_MASK_CEP
import br.com.truckhospital.modules.util.EDIT_TEXT_MASK_CPF
import br.com.truckhospital.modules.util.EDIT_TEXT_MASK_PHONE_NUMBER
import br.com.truckhospital.modules.util.extension.installMask

class ClientFragment : BaseFragment<ClientPresenter>(), ClientContract.View {

    private var binding: FragmentClientBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientBinding.inflate(layoutInflater, container, false)
        setPresenter(ClientPresenter(this))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.fragmentClientCpf?.installMask(EDIT_TEXT_MASK_CPF)
        binding?.fragmentClientNumber?.installMask(EDIT_TEXT_MASK_PHONE_NUMBER)
        binding?.fragmentClientCep?.installMask(EDIT_TEXT_MASK_CEP)
    }
}