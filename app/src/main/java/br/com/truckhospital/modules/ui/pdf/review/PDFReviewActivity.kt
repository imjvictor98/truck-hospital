package br.com.truckhospital.modules.ui.pdf.review

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.truckhospital.databinding.OrderTemplateBinding
import br.com.truckhospital.modules.core.model.Order
import br.com.truckhospital.modules.ui.base.activity.BaseActivity

class PDFReviewActivity: BaseActivity<PDFReviewPresenter>(), PDFReviewContract.View {

    companion object {
        private const val EXTRA_ORDER = "EXTRA_ORDER"
        fun start(context: Context, order: Order) {
            val intent = Intent(context, PDFReviewActivity::class.java).apply {
                putExtra(EXTRA_ORDER, order)
            }
            context.startActivity(intent)
        }
    }

    private lateinit var binding: OrderTemplateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = OrderTemplateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter(PDFReviewPresenter(this))
    }
}