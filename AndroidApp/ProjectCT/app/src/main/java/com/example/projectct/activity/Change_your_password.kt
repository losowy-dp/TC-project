package com.example.projectct.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.InterfaceAPI.ApiClient
import com.example.projectct.InterfaceAPI.SessionManager
import com.example.projectct.R
import com.example.projectct.helpClass.User.Password
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class Change_your_password : AppCompatActivity() {
    lateinit var oldpassword: EditText
    lateinit var newpassword: EditText
    lateinit var againpassword: EditText
    lateinit var apiClient: ApiClient
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_your_password)

        val buttonSaveChange = findViewById<Button>(R.id.but_save_change2)
        val buttonBackToEditProfile = findViewById<Button>(R.id.but_back_to_edit_profile)

        buttonSaveChange.setOnClickListener(buttonSaveChangeListener)
        buttonBackToEditProfile.setOnClickListener(buttonBackToEditProfileListener)
    }
    private val buttonSaveChangeListener = View.OnClickListener { saveChange() }
    private val buttonBackToEditProfileListener = View.OnClickListener { backToEditProfileListener() }

    private fun saveChange(){
         oldpassword = findViewById(R.id.old_password_editText)
         newpassword = findViewById(R.id.first_new_password_editText)
         againpassword = findViewById(R.id.second_new_password_editText)
        sessionManager = SessionManager(this)
        if(errorCheck()){
            apiClient = ApiClient()
            apiClient.getApiService().resetPassword(token = "Bearer ${sessionManager.fetchAuthToken()}",Password(newpassword.text.toString(),oldpassword.text.toString())).enqueue(object : Callback<Int>{
                override fun onResponse(call: Call<Int>, response: Response<Int>) {

                }

                override fun onFailure(call: Call<Int>, t: Throwable) {

                }

            })
        }
    }
    private fun errorCheck(): Boolean{
        if(newpassword.text.length < 8 && !Pattern.matches("^[a-zA-Z0-9]+\$",newpassword.text.toString())){
            Toast.makeText(this,R.string.errorRegNotGoodPass,Toast.LENGTH_SHORT).show()
            return false
        }
        if(oldpassword.text.equals(newpassword.text)){
            Toast.makeText(this,R.string.errorNewpassSameOldpass,Toast.LENGTH_SHORT).show()
            return false
        }
        if(newpassword.text.toString() != againpassword.text.toString()){
            Toast.makeText(this,R.string.errorRegNotGoodPass,Toast.LENGTH_SHORT).show()
            return false
        }
        if(TextUtils.isEmpty(oldpassword.text.toString().trim()))
        {
            Toast.makeText(this,R.string.erroremptyoldpassword,Toast.LENGTH_SHORT).show()
            return false
        }
        if(TextUtils.isEmpty(newpassword.text.toString().trim()) || TextUtils.isEmpty(againpassword.text.toString().trim()))
        {
            Toast.makeText(this,R.string.erroremptynewpassword,Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun backToEditProfileListener(){
        val intent = Intent(this, Edit::class.java)
        startActivity(intent)
    }


}