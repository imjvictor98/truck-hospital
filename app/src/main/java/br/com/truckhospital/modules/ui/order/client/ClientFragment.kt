package br.com.truckhospital.modules.ui.order.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import br.com.truckhospital.R
import br.com.truckhospital.databinding.FragmentClientBinding
import br.com.truckhospital.modules.core.model.Client
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.order.flows.create.CreateOrderActivity
import br.com.truckhospital.modules.util.ConstantUtil.EDIT_TEXT_MASK_CEP
import br.com.truckhospital.modules.util.ConstantUtil.EDIT_TEXT_MASK_CNPJ
import br.com.truckhospital.modules.util.ConstantUtil.EDIT_TEXT_MASK_PHONE_NUMBER
import br.com.truckhospital.modules.util.PairUtil.pairOf
import br.com.truckhospital.modules.util.extension.gone
import br.com.truckhospital.modules.util.extension.installMask

class ClientFragment(
    private val client: Client? = null,
    private val isReadMode: Boolean = false
) : BaseFragment<ClientPresenter>(), ClientContract.View {

    companion object {
        const val EXTRA_CLIENT = "EXTRA_CLIENT"
        const val REQUEST_EXTRA_CLIENT = "REQUEST_EXTRA_CLIENT"
    }

    private var binding: FragmentClientBinding? = null
    private var activity: CreateOrderActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientBinding.inflate(layoutInflater, container, false)
        activity = requireActivity() as? CreateOrderActivity
        setPresenter(ClientPresenter(this))
        getPresenter()?.checkMode(isReadMode, client)
        return binding?.root
    }

    override fun onResume() {
        setNextButton(getPresenter()?.isAllFieldsFilled() == true)
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPresenter()?.checkMode(isReadMode, client)
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

    override fun applyReadMode() {
        binding?.fragmentClientCnpj?.apply {
            isEnabled = false
            setText(client?.cnpj)
        }
        binding?.fragmentClientNumber?.apply {
            isEnabled = false
            setText(client?.phone)
        }
        binding?.fragmentClientCep?.apply {
            isEnabled = false
            setText(client?.cep)
        }
        binding?.fragmentClientName?.apply {
            isEnabled = false
            setText(client?.name)
        }
        binding?.fragmentClientFab?.gone()

    }

    override fun applyEditMode() {
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
                setFragmentResult(REQUEST_EXTRA_CLIENT, bundleOf(pairOf(EXTRA_CLIENT, Order(client = it))))
                activity?.goForward()
            }
        }
    }
}