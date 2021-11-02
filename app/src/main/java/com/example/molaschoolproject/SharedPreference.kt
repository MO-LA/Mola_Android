package com.example.molaschoolproject

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE

class SharedPreference(context: Context) {
    private val SharedPreferenceNm = "mPref"
    private val SharedPreference = context.getSharedPreferences(SharedPreferenceNm, MODE_PRIVATE)

    var token: String?
        get() = SharedPreference.getString("token", null)
        set(value) {
            SharedPreference.edit().putString("token", value).apply()
        }

}

