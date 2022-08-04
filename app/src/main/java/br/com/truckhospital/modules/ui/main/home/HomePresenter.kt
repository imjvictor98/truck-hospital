package br.com.truckhospital.modules.ui.main.home

import android.telephony.PhoneNumberUtils
import br.com.truckhospital.modules.core.database.RealTimeDataBase
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.core.repository.OrderRepositoryImpl
import br.com.truckhospital.modules.util.FirebaseAuthHelper
import br.com.truckhospital.modules.util.extension.NumberUtil
import br.com.truckhospital.modules.util.extension.toListOf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import timber.log.Timber

class HomePresenter(override val view: HomeContract.View?) : HomeContract.Presenter {
    override fun getPhoneNumber() {
        val numberWithoutCountryCode =
            PhoneNumberUtils.formatNumber(FirebaseAuthHelper.userAuth.currentUser?.phoneNumber, "55")
                .removePrefix("+55")
                .removePrefix(" ")
        view?.setDescription(numberWithoutCountryCode)
    }

    override fun signOut() {
        FirebaseAuthHelper.signOut()
        view?.goToSplash()
    }

    override fun createListenerForOrderList() {
        FirebaseAuthHelper.getUserId()?.let { uid ->
            view?.startSkeletonOrderList()
            view?.startSkeletonBudget()
            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val orderList = mutableListOf<Order>()
                    dataSnapshot.children.forEach {
                        it.getValue<Order>()?.also { order ->
                            orderList.add(order)
                        }
                    }

                    if (orderList.isNotEmpty()) {
                        view?.stopSkeletonOrderList()
                        view?.stopSkeletonBudget()
                        view?.setOrdersList(orderList.toListOf())
                        calculateEarns(orderList.toListOf())
                        Timber.d("HomePresenter: size of orders list = ${orderList.size}")
                    } else {
                        view?.stopSkeletonOrderList()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Timber.e(databaseError.message, "loadPost:onCancelled", databaseError.toException())
                    view?.stopSkeletonOrderList()
                }
            }
            RealTimeDataBase.dataBase.reference.child(uid).child(OrderRepositoryImpl.ORDERS).addValueEventListener(listener)
        }
    }

    override fun calculateEarns(orders: List<Order>) {
        var currentEarns = 0.0

        orders.forEach { order ->
            order.budget?.run {
                currentEarns += totalCost
            }
        }

        if (currentEarns > 0.0) {
            NumberUtil.getStringFormattedByCurrency(2, currentEarns)?.let {
                view?.setBudgetEarns(it)
            }
        }
    }
}