package br.com.truckhospital.modules.ui.order.flows.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.truckhospital.databinding.ActivityCreateOrderBinding
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.activity.BaseActivity
import br.com.truckhospital.modules.ui.order.OrderPage
import br.com.truckhospital.modules.ui.order.OrderPageEnum
import br.com.truckhospital.modules.ui.order.budget.BudgetFragment
import br.com.truckhospital.modules.ui.order.client.ClientFragment
import br.com.truckhospital.modules.ui.order.description.DescriptionFragment
import br.com.truckhospital.modules.ui.order.vehicle.VehicleFragment
import br.com.truckhospital.modules.ui.success.SuccessActivity
import br.com.truckhospital.modules.util.DialogUtil
import br.com.truckhospital.modules.util.PageTransformerUtil
import br.com.truckhospital.modules.util.extension.onPageSelected

class CreateOrderActivity : BaseActivity<CreateOrderContract.Presenter>(), CreateOrderContract.View {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CreateOrderActivity::class.java))
        }
    }

    private lateinit var binding: ActivityCreateOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityOrderToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupViewPager2()
        setPresenter(CreateOrderPresenter(this))
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

    private fun setupViewPager2() {
        binding.activityOrderViewPager.apply {
            adapter = OrderSliderAdapter()
            isUserInputEnabled = false
            binding.activityOrderDotsIndicator.attachTo(this)
            setPageTransformer(PageTransformerUtil.zoomOutPageTransformer)
            offscreenPageLimit = OrderPage.pagesForCreation.size
            onPageSelected { position ->
                supportActionBar?.title = OrderPage.pagesForCreation[position].title
            }
        }
    }

    fun goForward() {
        if (binding.activityOrderViewPager.currentItem >= 0) {
            binding.activityOrderViewPager.currentItem += 1
        }
    }

    inner class OrderSliderAdapter : FragmentStateAdapter(this@CreateOrderActivity) {
        override fun getItemCount() = OrderPage.pagesForCreation.size

        override fun createFragment(position: Int) = when(OrderPage.pagesForCreation[position]) {
            OrderPageEnum.ORDER_PAGE_CLIENT -> ClientFragment()
            OrderPageEnum.ORDER_PAGE_VEHICLE -> VehicleFragment()
            OrderPageEnum.ORDER_PAGE_BUDGET -> BudgetFragment()
            else -> DescriptionFragment(OrderPage.pagesForCreation[position])
        }
    }



}