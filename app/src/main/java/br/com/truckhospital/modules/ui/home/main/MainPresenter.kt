package br.com.truckhospital.modules.ui.home.main

import br.com.truckhospital.modules.core.database.RealTimeDataBase
import br.com.truckhospital.modules.core.database.RepositoryImpl
import br.com.truckhospital.modules.core.model.Order

class MainPresenter(override val view: MainContract.View?): MainContract.Presenter {
    private val repository by lazy { RepositoryImpl(RealTimeDataBase.dataBase.reference) }

    override fun foo(list: List<Order>) {
        repository.addOrder(list.first())
    }
}