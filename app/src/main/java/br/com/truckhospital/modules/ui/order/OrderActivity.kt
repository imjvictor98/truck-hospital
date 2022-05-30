package br.com.truckhospital.modules.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivityOrderBinding
import br.com.truckhospital.modules.ui.base.BaseActivity
import br.com.truckhospital.modules.util.PageTransformerUtil

class OrderActivity : BaseActivity<OrderContract.Presenter>(), OrderContract.View {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, OrderActivity::class.java))
        }
    }

    private val enums = listOf(
        OrderPageEnum.ORDER_PAGE_CLIENT,
        OrderPageEnum.ORDER_PAGE_VEHICLE,
        OrderPageEnum.ORDER_PAGE_COMPLAIN,
        OrderPageEnum.ORDER_PAGE_SERVICE,
        OrderPageEnum.ORDER_PAGE_BUDGET
    )
    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCustomActionBar(binding.activityOrderToolbar, R.drawable.md_nav_back)
        binding.activityOrderViewPager.apply {
            adapter = OrderSliderAdapter()
            binding.activityOrderDotsIndicator.attachTo(this)
            setPageTransformer(PageTransformerUtil.ZoomOutPageTransformer())
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    supportActionBar?.title = enums[position].title
                    super.onPageSelected(position)
                }
            })
        }
        setPresenter(OrderPresenter(this))
    }

    inner class OrderSliderAdapter : FragmentStateAdapter(this@OrderActivity) {
        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment = OrderFragment.getInstance(enums[position])
    }

    enum class OrderPageEnum(val title: String) {
        ORDER_PAGE_CLIENT("Cliente"),
        ORDER_PAGE_VEHICLE("Veículo"),
        ORDER_PAGE_COMPLAIN("Reclamação"),
        ORDER_PAGE_SERVICE("Serviço"),
        ORDER_PAGE_BUDGET("Total de Gastos")
    }
}