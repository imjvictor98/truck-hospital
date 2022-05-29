package br.com.truckhospital.modules.ui.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivityLoginBinding
import br.com.truckhospital.modules.ui.base.BaseActivity
import br.com.truckhospital.modules.ui.confirmation.ConfirmationActivity
import br.com.truckhospital.modules.util.DialogUtil
import br.com.truckhospital.modules.util.FirebaseAuthHelper
import br.com.truckhospital.modules.util.PhoneMaskUtil
import br.com.truckhospital.modules.util.extension.gone
import br.com.truckhospital.modules.util.extension.showSnackBar
import br.com.truckhospital.modules.util.extension.visible
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult


class LoginActivity :
    BaseActivity<LoginContract.Presenter>(), LoginContract.View,
    ActivityResultCallback<FirebaseAuthUIAuthenticationResult>
{

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCustomActionBar(binding.activityLoginToolbar, R.drawable.md_nav_back)
        setPresenter(LoginPresenter(this))

        binding.firebaseUiAuthPhoneInput.apply {
            addTextChangedListener(PhoneMaskUtil.build(this))
        }

        binding.firebaseUiAuthPhoneBtn.setOnClickListener {
            getPresenter()?.validatePhone(
                binding.firebaseUiAuthPhoneCountryCodeInput.text.toString(),
                binding.firebaseUiAuthPhoneInput.text.toString(),
                FirebaseAuthHelper.phoneAuthOptionsBuilder(this)
            )
        }
    }

    override fun onActivityResult(result: FirebaseAuthUIAuthenticationResult?) {
        when (result?.resultCode) {
            Activity.RESULT_OK -> {
                binding.root.showSnackBar(getString(R.string.sms_snack_bar))
            } else -> {
                showError()
            }
        }
    }

    override fun showCircularLoading() {
        binding.firebaseUiAuthPhoneBtn.gone()
        binding.firebaseUiAuthPhoneLoading.visible()
    }

    override fun hideCircularLoading() {
        binding.firebaseUiAuthPhoneBtn.visible()
        binding.firebaseUiAuthPhoneLoading.gone()
    }

    override fun showError() {
        DialogUtil.showDialog(
            context = mContext,
            title = getString(R.string.dialog_default_error_title),
            message = getString(R.string.dialog_default_error_message),
            positiveText = getString(R.string.dialog_default_error_btn),
            positiveCallback = { dialog, _ -> dialog.dismiss() }
        )
    }

    override fun showVerification(verificationId: String) {
        ConfirmationActivity.start(mContext, verificationId)
    }
}