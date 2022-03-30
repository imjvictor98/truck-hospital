package br.com.truckhospital.modules.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity<T>: AppCompatActivity(), BaseContract.BaseView {
    lateinit var mContext: Context
    private var mPresenter: BaseContract.BasePresenter<out BaseContract.BaseView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@BaseActivity
    }

    override fun onDestroy() {
        mPresenter = null
        super.onDestroy()
    }

    @Suppress("UNCHECKED_CAST")
    fun getPresenter(): T? {
        return mPresenter as T?
    }

    fun setPresenter(presenter: BaseContract.BasePresenter<out BaseContract.BaseView>?) {
        mPresenter = presenter
    }
}