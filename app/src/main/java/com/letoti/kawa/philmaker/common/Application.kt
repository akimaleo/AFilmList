package com.letoti.kawa.philmaker.common

import android.app.Application
import com.letoti.kawa.philmaker.util.UserData

/**
 * Created by kawa on 29.01.2018.
 */

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        UserData.instance.init(this)
    }
}