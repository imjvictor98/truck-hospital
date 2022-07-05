package br.com.truckhospital.modules.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.truckhospital.databinding.FragmentHomeBinding
import br.com.truckhospital.modules.core.database.RealTimeDataBase
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.core.repository.OrderRepositoryImpl
import br.com.truckhospital.modules.ui.base.fragment.BaseFragment
import br.com.truckhospital.modules.ui.main.MainActivity
import br.com.truckhospital.modules.ui.splash.SplashActivity
import br.com.truckhospital.modules.util.FirebaseAuthHelper
import br.com.truckhospital.modules.util.extension.requireOwnerActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import timber.log.Timber

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

        binding.fragmentHomeAvatar.setOnClickListener {
            requireOwnerActivity<MainActivity>()?.navigateToProfile()
        }

        binding.fragmentHomeExit.setOnClickListener {
            getPresenter()?.signOut()
        }

        registerEventValueListener()
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

    private fun registerEventValueListener() {
        FirebaseAuthHelper.getUserId()?.let { uid ->
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val post = dataSnapshot.getValue<List<Order>>()
                    //update recycler view list
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.e(databaseError.message, "loadPost:onCancelled", databaseError.toException())
                }
            }
            RealTimeDataBase.getChildReferenceAt(uid, OrderRepositoryImpl.ORDERS).addValueEventListener(postListener)
        }
    }
}