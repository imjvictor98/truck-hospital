package br.com.truckhospital.modules.ui.order

import androidx.annotation.IdRes
import br.com.truckhospital.R

enum class OrderPageEnum(val title: String, @IdRes val icon: Int? = null) {
    ORDER_PAGE_CLIENT("Cliente", R.drawable.ic_client),
    ORDER_PAGE_VEHICLE("Veículo", R.drawable.ic_vehicle),
    ORDER_PAGE_COMPLAIN("Reclamação"),
    ORDER_PAGE_SERVICE("Serviço"),
    ORDER_PAGE_BUDGET("Total de Gastos"),
    ORDER_PAGE_ACTIONS("Ações", R.drawable.ic_actions),
    ORDER_PAGE_DISCRETIONARY("Detalhes", R.drawable.ic_details)
}

object OrderPage {
    val pagesForCreation = listOf(
        OrderPageEnum.ORDER_PAGE_CLIENT,
        OrderPageEnum.ORDER_PAGE_VEHICLE,
        OrderPageEnum.ORDER_PAGE_COMPLAIN,
        OrderPageEnum.ORDER_PAGE_SERVICE,
        OrderPageEnum.ORDER_PAGE_BUDGET
    )

    val pagesForShow = listOf(
        OrderPageEnum.ORDER_PAGE_CLIENT,
        OrderPageEnum.ORDER_PAGE_VEHICLE,
        OrderPageEnum.ORDER_PAGE_DISCRETIONARY,
        OrderPageEnum.ORDER_PAGE_ACTIONS
    )
}
