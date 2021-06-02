package com.example.projectct.helpClass.Dane

import android.content.Context
import android.content.SharedPreferences
import com.example.projectct.InterfaceAPI.SessionManager
import com.example.projectct.R

class DaneOrder(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)
    //TODO: Add save from, where
    fun clear(){
        val editor = prefs.edit()
        editor.putString("nameW", null)
        editor.putString("longitudeW",null)
        editor.putString("latitudeW", null)
        editor.putString("nameF", null)
        editor.putString("longitudeF",null)
        editor.putString("latitudeF", null)
        editor.apply()
    }
    fun saveFrom(name: String, latitude: String, longitude: String){
        val editor = prefs.edit()
        editor.putString("nameF", name)
        editor.putString("longitudeF",longitude)
        editor.putString("latitudeF", latitude)
        editor.apply()
    }

    fun saveWhere(name: String, latitude: String, longitude: String){
        val editor = prefs.edit()
        editor.putString("nameW", name)
        editor.putString("longitudeW",longitude)
        editor.putString("latitudeW", latitude)
        editor.apply()
    }

    fun fetchFrom(): String?{
        return prefs.getString("nameF",null)+";" + prefs.getString("longitudeF",null) + ";" + prefs.getString("latitudeF",null)
    }
    fun fetchWhere(): String?{
        return prefs.getString("nameW",null)+";" + prefs.getString("longitudeW",null) + ";" + prefs.getString("latitudeW",null)
    }
}