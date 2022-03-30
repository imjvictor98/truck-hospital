package br.com.truckhospital.modules.auth

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import br.com.truckhospital.R
import br.com.truckhospital.databinding.ActivityLoginBinding
import br.com.truckhospital.modules.base.BaseActivity
import br.com.truckhospital.modules.util.DialogUtil
import br.com.truckhospital.modules.util.PhoneMaskUtil
import br.com.truckhospital.modules.util.extension.gone
import br.com.truckhospital.modules.util.extension.visible
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
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

//    private val signIn = registerForActivityResult(FirebaseAuthUIActivityResultContract(), this)
//
//    private fun startSignIn() {
//        val signInIntent = AuthUI.getInstance()
//            .createSignInIntentBuilder()
//            .setTheme(R.style.Theme_TruckHospital)
//            .setLogo(R.drawable.ic_truck_logo)
//            .setAvailableProviders(listOf(AuthUI.IdpConfig.PhoneBuilder().build()))
//            .build()
//
////        signIn.launch(signInIntent)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        startSignIn()
        setPresenter(LoginPresenter(this))

        binding.firebaseUiAuthPhoneInput.apply {
            addTextChangedListener(PhoneMaskUtil.build(this))
        }

        binding.firebaseUiAuthPhoneBtn.setOnClickListener {
            getPresenter()?.validatePhone(binding.firebaseUiAuthPhoneInput.text.toString(), this)
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
        binding.firebaseUiAuthViewFlipper.showNext()
    }
}