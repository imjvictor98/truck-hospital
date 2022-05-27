package br.com.truckhospital.modules.ui.home.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivityMainBinding
import br.com.truckhospital.modules.core.model.Client
import br.com.truckhospital.modules.core.model.Complaint
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.core.model.Vehicle
import br.com.truckhospital.modules.ui.base.BaseActivity

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
        binding.foo.setOnClickListener {
            foo()
        }
    }

    override fun foo() {
        val client = Client(
            "xxxxxx","xxxxxx","xxxxxx","xxxxxx"
        )
        val vehicle = Vehicle("xxxxx","xxxxx","xxxxx")
        val complaint = Complaint("xxxxx")
        val order = Order(
            "123456",
            "xxxxx",
            1F,2F,3F,
            client, vehicle, complaint
        )
        getPresenter()?.foo(listOf(order))
    }
}