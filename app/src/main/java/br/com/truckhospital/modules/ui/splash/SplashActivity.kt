package br.com.truckhospital.modules.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.truckhospital.databinding.ActivitySplashBinding
import br.com.truckhospital.modules.ui.auth.LoginActivity
import br.com.truckhospital.modules.ui.base.BaseActivity
import br.com.truckhospital.modules.ui.home.main.MainActivity

class SplashActivity : BaseActivity<SplashContract.Presenter>(), SplashContract.View {
    private lateinit var binding: ActivitySplashBinding

    companion object {
        fun startClearTask(context: Context) {
            val intent = Intent(context, SplashActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter(SplashPresenter(this))
        getPresenter()?.init()
    }

    override fun onButtonBehavior() {
        binding.splashLoginBtn.setOnClickListener {
            getPresenter()?.checkUser()
        }
    }

    override fun goToHome() {
        MainActivity.start(mContext)
        finish()
    }

    override fun goToLogin() {
        LoginActivity.start(mContext)
        finish()
    }
}