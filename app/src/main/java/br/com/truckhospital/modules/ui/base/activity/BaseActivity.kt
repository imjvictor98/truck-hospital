package br.com.truckhospital.modules.ui.base.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity<T>: AppCompatActivity(), BaseActivityContract.BaseView {
    lateinit var mContext: Context
    private var mPresenter: BaseActivityContract.BasePresenter<out BaseActivityContract.BaseView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@BaseActivity
    }

    override fun onDestroy() {
        mPresenter = null
        super.onDestroy()
    }

    @Suppress("UNCHECKED_CAST")
    fun getPresenter(): T? = mPresenter as T?

    fun setPresenter(presenter: BaseActivityContract.BasePresenter<out BaseActivityContract.BaseView>?) {
        mPresenter = presenter
    }
}