package br.com.truckhospital.modules.ui.order.action

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.truckhospital.databinding.FragmentActionBinding
import br.com.truckhospital.modules.core.model.ActionListener
import br.com.truckhospital.modules.core.model.ActionType
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment

class ActionFragment(
    private val mListener: ActionListener? = null
): ActionContract.View, BaseFragment<ActionContract.Presenter>() {

    private var _binding: FragmentActionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentActionBinding.inflate(inflater, container, false)
        setPresenter(ActionPresenter(this))
        getPresenter()?.start()
        return binding.root
    }

    override fun setActionList(actions: List<ActionType>) {
        binding.fragmentActionRecycler.adapter = ActionAdapter(actions, object : ActionAdapter.ActionListener {
            override fun onClick(type: ActionType) {
                when (type) {
                    ActionType.WHATSAPP -> {
                        mListener?.onCreateAndSendWhatsApp()
                    }

                    else -> {
                        mListener?.onCreatePDFCLick()
                    }
                }
            }
        })
    }
}