package br.com.truckhospital.modules.ui.base.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment<T>() : Fragment(), BaseFragmentContract.BaseView {
    lateinit var mContext: Context
    private var mPresenter: BaseFragmentContract.BasePresenter<out BaseFragmentContract.BaseView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireContext()
    }

    override fun onDestroy() {
        mPresenter = null
        super.onDestroy()
    }

    @Suppress("unchecked_cast")
    fun getPresenter(): T? = mPresenter as? T

    fun setPresenter(presenter: BaseFragmentContract.BasePresenter<out BaseFragmentContract.BaseView>?) {
        mPresenter = presenter
    }
}