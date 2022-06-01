package br.com.truckhospital.modules.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.truckhospital.databinding.ActivityOrderBinding
import br.com.truckhospital.modules.core.model.Client
import br.com.truckhospital.modules.core.model.Complaint
import br.com.truckhospital.modules.core.model.Vehicle
import br.com.truckhospital.modules.ui.base.activity.BaseActivity
import br.com.truckhospital.modules.ui.order.budget.BudgetFragment
import br.com.truckhospital.modules.ui.order.client.ClientFragment
import br.com.truckhospital.modules.ui.order.description.DescriptionFragment
import br.com.truckhospital.modules.ui.order.vehicle.VehicleFragment
import br.com.truckhospital.modules.util.DialogUtil
import br.com.truckhospital.modules.util.PageTransformerUtil
import br.com.truckhospital.modules.util.extension.installOnPageSelected

class OrderActivity : BaseActivity<OrderContract.Presenter>(), OrderContract.View {
    companion object {
        private val pages = listOf(
            Pair(OrderPageEnum.ORDER_PAGE_BUDGET, BudgetFragment()),
            Pair(OrderPageEnum.ORDER_PAGE_CLIENT, ClientFragment()),
            Pair(OrderPageEnum.ORDER_PAGE_VEHICLE, VehicleFragment()),
            Pair(OrderPageEnum.ORDER_PAGE_COMPLAIN, DescriptionFragment(OrderPageEnum.ORDER_PAGE_COMPLAIN)),
            Pair(OrderPageEnum.ORDER_PAGE_SERVICE, DescriptionFragment(OrderPageEnum.ORDER_PAGE_SERVICE)),
            Pair(OrderPageEnum.ORDER_PAGE_BUDGET, BudgetFragment())
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

    fun setClient(client: Client) {
        getPresenter()?.setClient(client)
    }

    fun setVehicle(vehicle: Vehicle) {
        getPresenter()?.setVehicle(vehicle)
    }

    fun setDescription(complaint: Complaint) {
        getPresenter()?.setComplain(complaint)
    }

    fun setService(complaint: Complaint) {
        getPresenter()?.setService(complaint)
    }

    private fun setupViewPager2() {
        binding.activityOrderViewPager.apply {
            adapter = OrderSliderAdapter()
            isUserInputEnabled = false
            binding.activityOrderDotsIndicator.attachTo(this)
            setPageTransformer(PageTransformerUtil.ZoomOutPageTransformer())
            offscreenPageLimit = pages.size
            installOnPageSelected { position ->
                supportActionBar?.title = pages[position].first.title
            }
        }
    }

    inner class OrderSliderAdapter : FragmentStateAdapter(this@OrderActivity) {
        override fun getItemCount() = pages.size

        override fun createFragment(position: Int): Fragment = pages[position].second
    }

    enum class OrderPageEnum(val title: String) {
        ORDER_PAGE_CLIENT("Cliente"),
        ORDER_PAGE_VEHICLE("Veículo"),
        ORDER_PAGE_COMPLAIN("Reclamação"),
        ORDER_PAGE_SERVICE("Serviço"),
        ORDER_PAGE_BUDGET("Total de Gastos")
    }

    override fun showDialog() {
        DialogUtil.showDialog(
            mContext,
            title = "Sair",
            message = "Quer mesmo descartar as alterações e sair?",
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
}