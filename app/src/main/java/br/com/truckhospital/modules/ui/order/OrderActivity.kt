package br.com.truckhospital.modules.ui.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivityOrderBinding
import br.com.truckhospital.modules.ui.base.BaseActivity

class OrderActivity : BaseActivity<OrderContract.Presenter>(), OrderContract.View {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, OrderActivity::class.java))
        }
    }

    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCustomActionBar(binding.activityOrderToolbar, R.drawable.md_nav_back)
        binding.activityOrderViewPager.apply {
            adapter = OrderSliderAdapter()
            binding.activityOrderDotsIndicator.attachTo(this)
        }
        setPresenter(OrderPresenter(this))
    }

    inner class OrderSliderAdapter : FragmentStateAdapter(this@OrderActivity) {
        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    OrderFragment.getInstance(OrderPageEnum.ORDER_PAGE_CLIENT)
                }
                1 -> {
                    OrderFragment.getInstance(OrderPageEnum.ORDER_PAGE_VEHICLE)
                }

                2 -> {
                    OrderFragment.getInstance(OrderPageEnum.ORDER_PAGE_COMPLAIN)
                }

                3 -> {
                    OrderFragment.getInstance(OrderPageEnum.ORDER_PAGE_SERVICE)
                }

                else -> {
                    OrderFragment.getInstance(OrderPageEnum.ORDER_PAGE_BUDGET)
                }
            }
        }

    }

    enum class OrderPageEnum {
        ORDER_PAGE_CLIENT,
        ORDER_PAGE_VEHICLE,
        ORDER_PAGE_COMPLAIN,
        ORDER_PAGE_SERVICE,
        ORDER_PAGE_BUDGET
    }
}