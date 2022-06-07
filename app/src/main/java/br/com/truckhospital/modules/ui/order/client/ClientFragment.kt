package br.com.truckhospital.modules.ui.order.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import br.com.truckhospital.databinding.FragmentClientBinding
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.order.OrderActivity
import br.com.truckhospital.modules.util.EDIT_TEXT_MASK_CEP
import br.com.truckhospital.modules.util.EDIT_TEXT_MASK_CNPJ
import br.com.truckhospital.modules.util.EDIT_TEXT_MASK_PHONE_NUMBER
import br.com.truckhospital.modules.util.extension.installMask

class ClientFragment : BaseFragment<ClientPresenter>(), ClientContract.View {

    private var binding: FragmentClientBinding? = null
    private var activity: OrderActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientBinding.inflate(layoutInflater, container, false)
        activity = requireActivity() as? OrderActivity
        setPresenter(ClientPresenter(this))
        return binding?.root
    }

    override fun onResume() {
        setNextButton(getPresenter()?.isAllFieldsFilled() == true)
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fragmentClientCnpj?.installMask(EDIT_TEXT_MASK_CNPJ) { _, extractedValue, _ ->
            getPresenter()?.setValidCNPJ(extractedValue)
        }

        binding?.fragmentClientNumber?.installMask(EDIT_TEXT_MASK_PHONE_NUMBER) { _, extractedValue, _ ->
            getPresenter()?.setValidPhoneNumber(extractedValue)
        }

        binding?.fragmentClientCep?.installMask(EDIT_TEXT_MASK_CEP) { _, extractedValue, _ ->
            getPresenter()?.setValidCEP(extractedValue)
        }

        binding?.fragmentClientName?.addTextChangedListener {
            getPresenter()?.setValidName(it.toString())
        }

        binding?.fragmentClientFab?.setOnClickListener {
            getPresenter()?.getClient(
                binding?.fragmentClientCnpj?.text.toString(),
                binding?.fragmentClientCep?.text.toString(),
                binding?.fragmentClientName?.text.toString(),
                binding?.fragmentClientNumber?.text.toString()
            )?.let {
                activity?.setClient(it)
                activity?.goForward()
            }
        }
    }

    override fun setNextButton(value: Boolean) {
        binding?.fragmentClientFab?.isEnabled = value
    }

    override fun setErrorCNPJ(errorText: String) {
        binding?.fragmentClientCnpj?.error = errorText
    }

    override fun setErrorCEP(errorText: String) {
        binding?.fragmentClientCep?.error = errorText
    }

    override fun setErrorName(errorText: String) {
        binding?.fragmentClientName?.error = errorText
    }

    override fun setErrorPhoneNumber(errorText: String) {
        binding?.fragmentClientNumber?.error = errorText
    }
}