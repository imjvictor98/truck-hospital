package br.com.truckhospital.modules.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splashLoginBtn.setOnClickListener {

        }
    }
}