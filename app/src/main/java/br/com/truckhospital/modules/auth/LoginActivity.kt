package br.com.truckhospital.modules.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivityLoginBinding
import br.com.truckhospital.modules.base.BaseActivity
import br.com.truckhospital.modules.confirmation.ConfirmationActivity
import br.com.truckhospital.modules.util.DialogUtil
import br.com.truckhospital.modules.util.FirebaseAuthHelper
import br.com.truckhospital.modules.util.PhoneMaskUtil
import br.com.truckhospital.modules.util.extension.gone
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
        //Result of authentication
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

    override fun showVerification() {
        ConfirmationActivity.start(mContext)
    }
}