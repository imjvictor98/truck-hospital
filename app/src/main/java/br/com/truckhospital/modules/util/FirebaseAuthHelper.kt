package br.com.truckhospital.modules.util

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

object FirebaseAuthHelper {
    fun phoneAuthOptionsBuilder(activity: Activity) = PhoneAuthOptions
        .newBuilder(FirebaseAuth.getInstance())
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(activity)

    fun getCredentialSMS(verificationId: String, smsCode: String) = PhoneAuthProvider
        .getCredential(verificationId, smsCode)

}