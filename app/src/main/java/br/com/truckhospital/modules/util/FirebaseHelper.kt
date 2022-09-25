package br.com.truckhospital.modules.util

import android.app.Activity
import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

object FirebaseHelper {
    val userAuth get() = FirebaseAuth.getInstance()

    fun phoneAuthOptionsBuilder(activity: Activity) = PhoneAuthOptions
        .newBuilder(FirebaseAuth.getInstance())
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(activity)

    fun getCredentialSMS(verificationId: String, smsCode: String) = PhoneAuthProvider
        .getCredential(verificationId, smsCode)

    fun getUserId() = userAuth.currentUser?.uid

    fun signOut() = userAuth.signOut()

    fun initializeAppCheck() {
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance())

    }

    fun initializeSafetyNet() {
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(SafetyNetAppCheckProviderFactory.getInstance())
    }

}