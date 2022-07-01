package br.com.truckhospital.modules.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivityMainBinding
import br.com.truckhospital.modules.ui.base.activity.BaseActivity
import br.com.truckhospital.modules.ui.order.OrderActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity() :BaseActivity<MainContract.Presenter>(), MainContract.View {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter(MainPresenter(this))
        binding.activityMainBottomNavView.background = null
        binding.activityMainFab.setOnClickListener {
            createOrder()
        }
        setupNavController()
    }

    override fun createOrder() {
        OrderActivity.start(mContext)
    }

    fun navigateToProfile() {
        binding.activityMainBottomNavView.selectedItemId = R.id.navigation_profile
    }

    private fun setupNavController() {
        val navView: BottomNavigationView = binding.activityMainBottomNavView

        val navController = findNavController(R.id.activity_main_nav_host_fragment)
        navView.setupWithNavController(navController)
    }
}