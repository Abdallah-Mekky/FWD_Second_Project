package com.task1.asteroidsappfwd.ui.myApp

import android.app.Application
import com.task1.asteroidsappfwd.ui.database.MyDataBase

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MyDataBase.initDataBase(context = this)
    }
}