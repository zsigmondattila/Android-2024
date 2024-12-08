package com.example.recipehub.utils

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

class SharedPreferences(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPref.edit()
        editor.putString("BEARER_TOKEN", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPref.getString("BEARER_TOKEN", null)
    }

    fun clearToken() {
        val editor = sharedPref.edit()
        editor.remove("BEARER_TOKEN")
        editor.apply()
    }

    fun saveUserData(token: String, userInfo: JSONObject) {
        val editor = sharedPref.edit()

        editor.putString("BEARER_TOKEN", token)

        editor.putString("GIVEN_NAME", userInfo.optString("given_name"))
        editor.putString("FAMILY_NAME", userInfo.optString("family_name"))
        editor.putString("PICTURE", userInfo.optString("picture"))

        editor.apply()
    }

    fun getGivenName(): String? {
        return sharedPref.getString("GIVEN_NAME", null)
    }

    fun getFamilyName(): String? {
        return sharedPref.getString("FAMILY_NAME", null)
    }

    fun getPicture(): String? {
        return sharedPref.getString("PICTURE", null)
    }

}
