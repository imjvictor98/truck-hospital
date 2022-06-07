package br.com.truckhospital.modules

import androidx.multidex.MultiDexApplication
import br.com.truckhospital.BuildConfig
import timber.log.Timber
import java.util.*

class MainApplication: MultiDexApplication() {
    companion object {
        var localeBRL = Locale("pt", "BR")
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(
            Timber.DebugTree()
        )
    }
}