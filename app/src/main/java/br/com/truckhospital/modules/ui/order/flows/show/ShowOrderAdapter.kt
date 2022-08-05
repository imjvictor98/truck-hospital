package br.com.truckhospital.modules.ui.order.flows.show

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.order.OrderPage
import br.com.truckhospital.modules.ui.order.OrderPageEnum
import br.com.truckhospital.modules.ui.order.client.ClientFragment
import br.com.truckhospital.modules.ui.order.vehicle.VehicleFragment

class ShowOrderAdapter(
    fa: FragmentActivity,
    val order: Order
): FragmentStateAdapter(fa) {

    override fun getItemCount() = OrderPage.pagesForShow.size

    override fun createFragment(position: Int): Fragment {
        return when (OrderPage.pagesForShow[position]) {
            OrderPageEnum.ORDER_PAGE_CLIENT -> {
                ClientFragment(order.client, true)
            }

            OrderPageEnum.ORDER_PAGE_VEHICLE -> {
                VehicleFragment(order.vehicle, true)
            }

            OrderPageEnum.ORDER_PAGE_DISCRETIONARY -> {
                VehicleFragment()
            }

            else -> {
                VehicleFragment()
            }

        }

    }
}