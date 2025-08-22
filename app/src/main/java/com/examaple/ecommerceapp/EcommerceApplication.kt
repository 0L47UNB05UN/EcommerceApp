package com.examaple.ecommerceapp

import android.app.Application
import com.examaple.ecommerceapp.data.AppContainer
import com.examaple.ecommerceapp.data.DefaultAppContainer

class EcommerceApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}