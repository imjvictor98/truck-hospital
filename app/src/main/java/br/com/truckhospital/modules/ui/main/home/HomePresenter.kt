package br.com.truckhospital.modules.ui.main.home

import android.telephony.PhoneNumberUtils
import br.com.truckhospital.modules.util.FirebaseAuthHelper
import com.google.firebase.auth.FirebaseAuth

class HomePresenter(override val view: HomeContract.View?) : HomeContract.Presenter {
    override fun getPhoneNumber() {
        val numberWithoutCountryCode =
            PhoneNumberUtils.formatNumber(FirebaseAuthHelper.userAuth.currentUser?.phoneNumber, "55")
                .removePrefix("+55")
                .removePrefix(" ")
        view?.setDescription(numberWithoutCountryCode)
    }

    override fun signOut() {
        FirebaseAuth.getInstance().signOut()
        view?.goToSplash()
    }
}