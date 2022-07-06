package br.com.truckhospital.modules.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.truckhospital.databinding.FragmentHomeBinding
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.main.MainActivity
import br.com.truckhospital.modules.ui.splash.SplashActivity
import br.com.truckhospital.modules.util.extension.requireOwnerActivity

class HomeFragment : BaseFragment<HomeContract.Presenter>(), HomeContract.View {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setPresenter(HomePresenter(this))
        getPresenter()?.getPhoneNumber()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPresenter()?.createListenerForOrderList()

        binding.fragmentHomeAvatar.setOnClickListener {
            requireOwnerActivity<MainActivity>()?.navigateToProfile()
        }

        binding.fragmentHomeExit.setOnClickListener {
            getPresenter()?.signOut()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun goToSplash() {
        SplashActivity.startClearTask(mContext)
    }

    override fun setDescription(text: String) {
        binding.fragmentHomeDescription.text = text
    }

    override fun setOrdersList(orders: List<Order>) {
        binding.fragmentHomeOrderRecyclerView.adapter = HomeOrderAdapter(orders)
    }
}