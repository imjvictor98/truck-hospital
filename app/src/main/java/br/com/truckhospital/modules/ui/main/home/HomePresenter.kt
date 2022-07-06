package br.com.truckhospital.modules.ui.main.home

import android.telephony.PhoneNumberUtils
import br.com.truckhospital.modules.core.database.RealTimeDataBase
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.core.repository.OrderRepositoryImpl
import br.com.truckhospital.modules.util.FirebaseAuthHelper
import br.com.truckhospital.modules.util.extension.toListOf
import com.google.firebase.auth.FirebaseAuth
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
        FirebaseAuth.getInstance().signOut()
        view?.goToSplash()
    }

    override fun createListenerForOrderList() {
        FirebaseAuthHelper.getUserId()?.let { uid ->
            view?.startSkeletonOrderList()
            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val orderList = mutableListOf<Order?>()
                    dataSnapshot.children.forEach {
                        val order: Order? = it.getValue<Order>()
                        orderList.add(order)
                    }

                    if (orderList.isNotEmpty()) {
                        view?.stopSkeletonOrderList()
                        view?.setOrdersList(orderList.toListOf())
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
}