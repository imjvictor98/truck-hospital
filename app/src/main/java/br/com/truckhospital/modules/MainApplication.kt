package br.com.truckhospital.modules

import androidx.multidex.MultiDexApplication
import br.com.truckhospital.BuildConfig
import timber.log.Timber

class MainApplication: MultiDexApplication() {
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