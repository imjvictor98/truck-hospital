package br.com.truckhospital.modules.confirmation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivityConfirmationBinding
import br.com.truckhospital.modules.base.BaseActivity
import br.com.truckhospital.modules.util.DialogUtil

class ConfirmationActivity:
    BaseActivity<ConfirmationContract.Presenter>(), ConfirmationContract.View {

    companion object {
        private const val EXTRA_VERIFICATION_ID = "EXTRA_VERIFICATION_ID"
        fun start(context: Context, verificationId: String) {
            val intent = Intent(context, ConfirmationActivity::class.java).apply {
                putExtra(EXTRA_VERIFICATION_ID, verificationId)
            }
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityConfirmationBinding
    private var mVerificationId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter(ConfirmationPresenter(this))

        intent?.extras?.apply {
            mVerificationId = this.getString(EXTRA_VERIFICATION_ID)
        }

        binding.activityConfirmationBtn.setOnClickListener {
            getPresenter()?.validateSms(mVerificationId!!, binding.activityConfirmationSmsEdittext.text.toString())
        }
    }

    override fun showButton() {
        binding.activityConfirmationBtn.isVisible = true
    }

    override fun hideButton() {
        binding.activityConfirmationBtn.isVisible = false
    }

    override fun showLoading() {
        binding.activityConfirmationLoading.isVisible = true
    }

    override fun hideLoading() {
        binding.activityConfirmationLoading.isVisible = true
    }

    override fun showDialogError(message: String?) {
        DialogUtil.showDialog(
            context = mContext,
            title = getString(R.string.dialog_default_error_title),
            message = message ?: getString(R.string.dialog_default_error_message),
            positiveText = getString(R.string.dialog_default_error_btn),
            positiveCallback = { dialog, _ -> dialog.dismiss() }
        )
    }
}