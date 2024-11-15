package com.example.inlist.app

import android.app.Application
import com.example.inlist.di.DaggerAppComponent

class App : Application() {
    val component by lazy { DaggerAppComponent.factory().create(this)}
}