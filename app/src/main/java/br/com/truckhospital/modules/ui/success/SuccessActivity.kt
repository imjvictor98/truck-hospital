package br.com.truckhospital.modules.ui.success

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivitySuccessBinding
import br.com.truckhospital.modules.util.extension.setColouredSpan

class SuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessBinding
    private var mOrderId = String()

    companion object {
        private const val EXTRA_ORDER_ID = "EXTRA_ORDER_ID"
        fun start(context: Context, orderId: String) {
            val intent = Intent(context, SuccessActivity::class.java).apply {
                putExtra(EXTRA_ORDER_ID, orderId)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mOrderId = intent.getStringExtra(EXTRA_ORDER_ID) ?: mOrderId
        binding.activitySuccessTitle.apply {
            text = getString(R.string.success_activity_title_format, mOrderId)
            this.setColouredSpan(mOrderId, ContextCompat.getColor(context, R.color.green_300))
        }
        binding.activitySuccessClose.setOnClickListener {
            finish()
        }
    }
}