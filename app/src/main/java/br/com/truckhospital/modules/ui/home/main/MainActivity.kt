package br.com.truckhospital.modules.ui.home.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivityMainBinding
import br.com.truckhospital.modules.ui.base.activity.BaseActivity
import br.com.truckhospital.modules.ui.order.OrderActivity
import br.com.truckhospital.modules.ui.splash.SplashActivity
import br.com.truckhospital.modules.util.FirebaseAuthHelper

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter(MainPresenter(this))
        setSupportActionBar(binding.activityMainToolbar)
        getPresenter()?.createList()
        supportActionBar?.apply {
            val removeCountryCoded = PhoneNumberUtils.formatNumber(FirebaseAuthHelper.userAuth.currentUser?.phoneNumber, "55").removePrefix("+55")
            subtitle = removeCountryCoded
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        menu.findItem(R.id.activity_main_menu_exit)?.apply {
            val drawableToChangeTint = DrawableCompat.wrap(this.icon)
            drawableToChangeTint.setTint(ContextCompat.getColor(mContext,R.color.white_100))
            icon = drawableToChangeTint
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.activity_main_menu_exit -> {
                getPresenter()?.signOut()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun goToOrder() {
        OrderActivity.start(mContext)
    }

    override fun goToSplash() {
        SplashActivity.startClearTask(mContext)
        finish()
    }

    override fun setMenuListAdapter(list: List<Pair<String, Int>>) {
        binding.activityMainList.adapter = MainActionListAdapter(list, object : MainActionListAdapter.MainActionMenuListener {
            override fun onClick() {
                OrderActivity.start(mContext)
            }
        })
    }
}