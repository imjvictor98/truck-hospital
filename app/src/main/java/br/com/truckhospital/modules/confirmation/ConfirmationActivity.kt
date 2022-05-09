package br.com.truckhospital.modules.confirmation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.truckhospital.databinding.ActivityConfirmationBinding
import br.com.truckhospital.modules.base.BaseActivity

class ConfirmationActivity:
    BaseActivity<ConfirmationContract.Presenter>(), ConfirmationContract.View {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ConfirmationActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter(ConfirmationPresenter(this))
    }
}