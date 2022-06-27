package br.com.truckhospital.modules.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.truckhospital.databinding.ActivityOrderBinding
import br.com.truckhospital.modules.core.model.*
import br.com.truckhospital.modules.ui.base.activity.BaseActivity
import br.com.truckhospital.modules.ui.home.main.MainActivity
import br.com.truckhospital.modules.ui.order.budget.BudgetFragment
import br.com.truckhospital.modules.ui.order.client.ClientFragment
import br.com.truckhospital.modules.ui.order.description.DescriptionFragment
import br.com.truckhospital.modules.ui.order.vehicle.VehicleFragment
import br.com.truckhospital.modules.ui.success.SuccessActivity
import br.com.truckhospital.modules.util.DialogUtil
import br.com.truckhospital.modules.util.PageTransformerUtil
import br.com.truckhospital.modules.util.extension.onPageSelected

class OrderActivity : BaseActivity<OrderContract.Presenter>(), OrderContract.View {

    companion object {
        private val pages = listOf(
            OrderPageEnum.ORDER_PAGE_CLIENT,
            OrderPageEnum.ORDER_PAGE_VEHICLE,
            OrderPageEnum.ORDER_PAGE_COMPLAIN,
            OrderPageEnum.ORDER_PAGE_SERVICE,
            OrderPageEnum.ORDER_PAGE_BUDGET
        )

        fun start(context: Context) {
            context.startActivity(Intent(context, OrderActivity::class.java))
        }
    }

    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityOrderToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViewPager2()
        setPresenter(OrderPresenter(this))
    }

    override fun onBackPressed() {
        if (binding.activityOrderViewPager.currentItem == 0) {
            showDialog()
        } else {
            binding.activityOrderViewPager.apply {
                setCurrentItem(currentItem - 1, true)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    fun goForward() {
        if (binding.activityOrderViewPager.currentItem >= 0) {
            binding.activityOrderViewPager.currentItem += 1
        }
    }

    private fun setupViewPager2() {
        binding.activityOrderViewPager.apply {
            adapter = OrderSliderAdapter()
            isUserInputEnabled = false
            binding.activityOrderDotsIndicator.attachTo(this)
            setPageTransformer(PageTransformerUtil.ZoomOutPageTransformer())
            offscreenPageLimit = pages.size
            onPageSelected { position ->
                supportActionBar?.title = pages[position].title
            }
        }
    }

    override fun showDialog() {
        DialogUtil.showDialog(
            mContext,
            title = "Sair",
            message = "Descartar as alterações e sair?",
            positiveText = "Sim",
            positiveCallback = { _, _ ->
                super.onBackPressed()
            },
            negativeText = "Não",
            negativeCallback = { dialog, _ ->
                dialog.dismiss()
            }
        )
    }

    override fun finishForm(order: Order) {
        getPresenter()?.createOrder(order)
    }

    override fun showSuccess(orderId: String) {
        SuccessActivity.start(mContext, orderId)
        finish()
    }

    inner class OrderSliderAdapter : FragmentStateAdapter(this@OrderActivity) {
        override fun getItemCount() = pages.size

        override fun createFragment(position: Int) = when(pages[position]) {
            OrderPageEnum.ORDER_PAGE_CLIENT -> ClientFragment()
            OrderPageEnum.ORDER_PAGE_VEHICLE -> VehicleFragment()
            OrderPageEnum.ORDER_PAGE_BUDGET -> BudgetFragment()
            else -> DescriptionFragment(pages[position])
        }
    }

    enum class OrderPageEnum(val title: String) {
        ORDER_PAGE_CLIENT("Cliente"),
        ORDER_PAGE_VEHICLE("Veículo"),
        ORDER_PAGE_COMPLAIN("Reclamação"),
        ORDER_PAGE_SERVICE("Serviço"),
        ORDER_PAGE_BUDGET("Total de Gastos")
    }

}