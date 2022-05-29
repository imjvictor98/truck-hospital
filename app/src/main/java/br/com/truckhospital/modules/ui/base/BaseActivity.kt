package br.com.truckhospital.modules.ui.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.truckhospital.R

open class BaseActivity<T>: AppCompatActivity(), BaseContract.BaseView {
    lateinit var mContext: Context
    private var mPresenter: BaseContract.BasePresenter<out BaseContract.BaseView>? = null
    private var mSupportActionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@BaseActivity
    }

    override fun onDestroy() {
        mPresenter = null
        super.onDestroy()
    }

    fun setCustomActionBar(toolbar: Toolbar, @DrawableRes navBack: Int? = null) {
        mSupportActionBar = supportActionBar.apply {
            setSupportActionBar(toolbar)
            if (navBack != null) {
                this?.setIcon(navBack)
                this?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    val getCustomActionBar: ActionBar? = mSupportActionBar

    @Suppress("UNCHECKED_CAST")
    fun getPresenter(): T? {
        return mPresenter as T?
    }

    fun setPresenter(presenter: BaseContract.BasePresenter<out BaseContract.BaseView>?) {
        mPresenter = presenter
    }
}