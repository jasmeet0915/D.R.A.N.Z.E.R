package com.developers.dranzer.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DranzerApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}