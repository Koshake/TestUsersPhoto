package com.koshake1.testusersphoto.retrofit

import android.content.SharedPreferences

class PreferenceHelper(
    private val preferences: SharedPreferences
) {
    fun setStatus(key: String, status: Boolean) {
        preferences.edit().putBoolean(key, status).apply()
    }

    fun isImage(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }
}