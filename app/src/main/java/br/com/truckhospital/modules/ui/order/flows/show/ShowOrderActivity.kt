package br.com.truckhospital.modules.ui.order.flows.show

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import br.com.truckhospital.databinding.ActivityShowOrderBinding
import br.com.truckhospital.modules.core.model.ActionListener
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.activity.BaseActivity
import br.com.truckhospital.modules.ui.order.OrderPage
import com.google.android.material.tabs.TabLayoutMediator

class ShowOrderActivity: ShowOrderContract.View, BaseActivity<ShowOrderContract.Presenter>() {

    companion object {
        private const val EXTRA_ORDER = "EXTRA_ORDER"
        fun start(context: Context, order: Order) {
            val intent = Intent(context, ShowOrderActivity::class.java).apply {
                putExtra(EXTRA_ORDER, order)
            }
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityShowOrderBinding
    private var mOrder: Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadExtras()
        binding.activityShowOrderToolbar.title = mOrder?.client?.name
        binding.activityShowOrderToolbar.subtitle = mOrder?.client?.cnpj
        setSupportActionBar(binding.activityShowOrderToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTabMediator()
        setPresenter(ShowOrderPresenter(this))
    }

    private fun loadExtras() {
        intent.extras?.apply {
            mOrder = get(EXTRA_ORDER) as? Order
        }
    }

    private fun setTabMediator() {
        binding.activityShowOrderViewPager.adapter = ShowOrderAdapter(this, mOrder!!, object : ActionListener {
            override fun onCreatePDFCLick() {

            }

            override fun onCreateAndSendWhatsApp() {

            }
        })


        TabLayoutMediator(binding.activityShowOrderTabLayout, binding.activityShowOrderViewPager) { tab, position ->

            OrderPage.pagesForShow[position].icon?.run {
                tab.icon = ContextCompat.getDrawable(mContext, this)
            }
        }.attach()
    }

}