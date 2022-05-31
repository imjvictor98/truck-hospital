package br.com.truckhospital.modules.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.truckhospital.databinding.ActivityOrderBinding
import br.com.truckhospital.modules.ui.base.activity.BaseActivity
import br.com.truckhospital.modules.ui.order.client.ClientFragment
import br.com.truckhospital.modules.ui.order.description.DescriptionFragment
import br.com.truckhospital.modules.ui.order.vehicle.VehicleFragment
import br.com.truckhospital.modules.util.PageTransformerUtil

class OrderActivity : BaseActivity<OrderContract.Presenter>(), OrderContract.View {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, OrderActivity::class.java))
        }
    }


    private val pages = listOf(
        Pair(OrderPageEnum.ORDER_PAGE_CLIENT, ClientFragment()),
        Pair(OrderPageEnum.ORDER_PAGE_VEHICLE, VehicleFragment()),
        Pair(OrderPageEnum.ORDER_PAGE_COMPLAIN, DescriptionFragment()),
        Pair(OrderPageEnum.ORDER_PAGE_SERVICE, DescriptionFragment()),
        Pair(OrderPageEnum.ORDER_PAGE_BUDGET, ClientFragment())
    )
    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityOrderToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.activityOrderViewPager.apply {
            adapter = OrderSliderAdapter()
            binding.activityOrderDotsIndicator.attachTo(this)
            setPageTransformer(PageTransformerUtil.ZoomOutPageTransformer())
            offscreenPageLimit = pages.size
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    supportActionBar?.title = pages[position].first.title
                    super.onPageSelected(position)
                }
            })
        }
        setPresenter(OrderPresenter(this))
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
}