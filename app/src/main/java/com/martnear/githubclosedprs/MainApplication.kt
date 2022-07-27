package com.martnear.githubclosedprs


import android.app.Application

class MainApplication : Application() {
    companion object {
        lateinit var INSTANCE: Application
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}
