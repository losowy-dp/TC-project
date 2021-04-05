package com.example.projectct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.projectct.activity.HomeActivity

class Edit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val buttonSaveChange = findViewById<Button>(R.id.but_save_change)
        val buttonChangePassword = findViewById<Button>(R.id.but_change_password)
        val buttonBackToMenu = findViewById<Button>(R.id.but_back_to_menu)

        buttonBackToMenu.setOnClickListener(buttonBackToMenuListener)
        buttonSaveChange.setOnClickListener(buttonSaveChangeListener)
        buttonChangePassword.setOnClickListener(buttonChangePasswordListener)
    }

    private val buttonBackToMenuListener = View.OnClickListener { homeActivity() }
    private val buttonSaveChangeListener = View.OnClickListener { saveChange() }
    private val buttonChangePasswordListener = View.OnClickListener { changePassword() }

    private fun homeActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun saveChange(){
        val fullname = findViewById<EditText>(R.id.editTextTextPersonName)
        val login = findViewById<EditText>(R.id.editTextTextPersonName2)
        val phonenumber = findViewById<EditText>(R.id.editTextTextPersonName3)
        val email = findViewById<EditText>(R.id.editTextTextPersonName4)

        if(fullname.text.length != 0){

        }else{

        }

        if(login.text.length != 0){

        }else{

        }

        if(phonenumber.text.length != 0){

        }else{

        }

        if(email.text.length != 0){

        }else{

        }
    }

    private fun changePassword(){
        val intent = Intent(this, Change_your_password::class.java)
        startActivity(intent)
        }


}