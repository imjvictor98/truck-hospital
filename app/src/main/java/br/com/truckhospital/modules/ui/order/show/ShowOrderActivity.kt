package br.com.truckhospital.modules.ui.order.show

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.truckhospital.databinding.ActivityShowOrderBinding
import br.com.truckhospital.modules.ui.base.activity.BaseActivity

class ShowOrderActivity: ShowOrderContract.View, BaseActivity<ShowOrderContract.Presenter>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ShowOrderActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityShowOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter(ShowOrderPresenter(this))
    }

}