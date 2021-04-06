package com.example.projectct.activity

import com.example.projectct.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class Edit : AppCompatActivity() {
    lateinit var buttonSaveChange: Button
    lateinit var fullname: EditText
    lateinit var phonenumber: EditText
    lateinit var email: EditText
    lateinit var login: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        buttonSaveChange = findViewById<Button>(R.id.but_save_change)
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
    //TODO change phone number with just text on phone
    private fun saveChange(){
        val fullname = findViewById<EditText>(R.id.editTextTextPersonName)
        val login = findViewById<EditText>(R.id.editTextTextPersonName2)
        val phonenumber = findViewById<EditText>(R.id.editTextTextPersonName3)
        val email = findViewById<EditText>(R.id.editTextTextPersonName4)
        if(fullname.isEnabled == false) {
            fullname.isEnabled = true
            login.isEnabled = true
            phonenumber.isEnabled = true
            email.isEnabled = true
            buttonSaveChange.setText(R.string.save)
        }
        else
        {
            if(errors())
            {
                //save data in database
                fullname.isEnabled = false
                login.isEnabled = false
                phonenumber.isEnabled = false
                email.isEnabled = false
                buttonSaveChange.setText(R.string.change_the_data)
            }
        }
    }
    private fun errors():Boolean{
        if(TextUtils.isEmpty(fullname.getText().toString().trim()) || TextUtils.isEmpty(login.getText().toString().trim()) || TextUtils.isEmpty(phonenumber.getText().toString().trim()) || TextUtils.isEmpty(email.getText().toString().trim()))
        {
            Toast.makeText(this, R.string.erroremptylines, Toast.LENGTH_SHORT).show()
            return false
        }
        if(phonenumber.text.length > 12 || phonenumber.text.length < 8)
        {
            Toast.makeText(this, R.string.errorphonenumber, Toast.LENGTH_SHORT).show()
            return false
        }
        if(login.text.length < 6)
        {
            Toast.makeText(this, R.string.errorLogin, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    private fun changePassword(){
        val intent = Intent(this, Change_your_password::class.java)
        startActivity(intent)
        }
    }