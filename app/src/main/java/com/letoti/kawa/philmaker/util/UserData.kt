package com.letoti.kawa.philmaker.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

/**
 * Created by kawa on 29.01.2018.
 */

class UserData private constructor() {

    private var mSharedPreferences: SharedPreferences? = null

    var accessToken: String
        get() {
            return mSharedPreferences!!.getString(ACCESS_TOKEN, "c33e25174af866c5c102772d92d0e480")
        }
        set(value) {
            val prefsEditor = mSharedPreferences!!.edit()
            prefsEditor.putString(ACCESS_TOKEN, value)
            prefsEditor.apply()
        }

    fun init(context: Context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCES_DATA, Context.MODE_PRIVATE)
    }

    companion object {
        private const val PREFERENCES_DATA: String = "data"
        private const val ACCESS_TOKEN: String = "access_token"

        val instance: UserData by lazy { Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = UserData()
    }
}