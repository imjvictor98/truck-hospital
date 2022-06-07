package br.com.truckhospital.modules.ui.home.main

import br.com.truckhospital.R
import br.com.truckhospital.modules.core.database.RealTimeDataBase
import br.com.truckhospital.modules.core.database.RepositoryImpl
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.util.FirebaseAuthHelper
import com.google.firebase.auth.FirebaseAuth

class MainPresenter(override val view: MainContract.View?): MainContract.Presenter {
    private val repository by lazy { RepositoryImpl(RealTimeDataBase.dataBase.reference) }

    override fun onCardClicked() {
        view?.goToOrder()
    }

    override fun signOut() {
        FirebaseAuth.getInstance().signOut()
        view?.goToSplash()
    }

    override fun createList() {
        view?.setMenuListAdapter(
            listOf(
                Pair("Nova ordem", R.drawable.ic_new_order)
            )
        )
    }
}