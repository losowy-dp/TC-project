package com.example.projectct.InterfaceAPI

import android.content.Context
import android.content.SharedPreferences
import com.example.projectct.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)

    companion object{
        const val  USER_TOKEN = "user_token"
        const val  USER_TOKEN_REFRESH = "refresh_token"
    }


    fun saveAuthToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }
    fun saveRefreshToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN_REFRESH,token)
        editor.apply()
    }
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN,null)
    }


    fun fetchAuthRefreshToken(): String?{
        return prefs.getString(USER_TOKEN_REFRESH,null)
    }

}