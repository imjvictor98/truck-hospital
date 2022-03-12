package br.com.truckhospital.modules.splash

import android.os.Bundle
import br.com.truckhospital.databinding.ActivitySplashBinding
import br.com.truckhospital.modules.auth.LoginActivity
import br.com.truckhospital.modules.base.BaseActivity

class SplashActivity : BaseActivity<SplashContract.Presenter>(), SplashContract.View {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter(SplashPresenter(this))
        getPresenter()?.init()
    }

    override fun onButtonBehavior() {
        binding.splashLoginBtn.setOnClickListener {
            LoginActivity.start(mContext)
        }
    }
}