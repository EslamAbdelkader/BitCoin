package com.eslam.bitcoin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Application class
 */
class BitCoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        /**
         * Not leaking, keeping the application context static, to provide for Dependency Injection
         */
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}