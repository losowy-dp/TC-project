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
import androidx.core.content.ContextCompat.startActivity
import com.example.projectct.InterfaceAPI.ApiClient
import com.example.projectct.InterfaceAPI.SessionManager
import com.example.projectct.helpClass.User.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class   Edit : AppCompatActivity() {
    lateinit var sessionManager: SessionManager
    lateinit var buttonSaveChange: Button
    lateinit var fullname: EditText
    lateinit var phonenumber: EditText
    lateinit var email: EditText
    lateinit var login: EditText
    lateinit var apiClient: ApiClient
    var id: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        id = intent.extras!!.getString("id").toString()
        fieldData()
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
    private fun fieldData(){
        fullname = findViewById<EditText>(R.id.editTextTextPersonName)
        login = findViewById<EditText>(R.id.editTextTextPersonName2)
        phonenumber = findViewById<EditText>(R.id.editTextTextPersonName3)
        email = findViewById<EditText>(R.id.editTextTextPersonName4)
        apiClient = ApiClient()
        sessionManager = SessionManager(this)
        apiClient.getApiService().takeInfoPrimitive(id).enqueue(object: Callback<List<UserPhone>>{
            override fun onResponse(call: Call<List<UserPhone>>, response: Response<List<UserPhone>>) {
                var dane = response.body()
                if (response.code() == 200) {
                    dane!!.forEach {
                        fullname.setText(it.user!!.first_name)
                        login.setText(it.user!!.last_name)
                        phonenumber.setText(it.number_of_phone)
                        email.setText(it.user!!.email)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserPhone>>, t: Throwable) {
            }

        })
    }
    private fun homeActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
    private fun saveChange(){
        if(fullname.isEnabled == false) {
            fullname.isEnabled = true
            login.isEnabled = true
            phonenumber.isEnabled = true
            email.isEnabled = true
            buttonSaveChange.setText(R.string.save)
        }
        else
        {
            if(true)
            {
                //save data in database
                fullname.isEnabled = false
                login.isEnabled = false
                phonenumber.isEnabled = false
                email.isEnabled = false
                buttonSaveChange.setText(R.string.change_the_data)
                //Costyl
                apiClient.getApiService().kostyl(id, Costyl(phonenumber.text.toString(),fullname.text.toString(),login.text.toString(),email.text.toString())).enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if(response.code()==200)
                            Toast.makeText(this@Edit,"Change",Toast.LENGTH_SHORT).show()
                        else Toast.makeText(this@Edit,"NOT Change", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
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
        if(email.text.length < 6 && android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()){
            Toast.makeText(this, R.string.errorEmail, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveData(){
        if(toDBProfile())
            Toast.makeText(this,R.string.data_changed,Toast.LENGTH_SHORT).show()
    }
    private fun toDBProfile(): Boolean{
        var odp: Boolean = false
        apiClient.getApiService().changeProfileInfo(UserPhonePrim(id,phonenumber.text.toString())).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
               if(response.code()==200){
                   odp =true
               }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
               odp = false
            }

        })
        return false
    }


    private fun toDBUser(): Boolean{
        return false
    }
    private fun changePassword(){
        val intent = Intent(this, Change_your_password::class.java)
        startActivity(intent)
        }
    }