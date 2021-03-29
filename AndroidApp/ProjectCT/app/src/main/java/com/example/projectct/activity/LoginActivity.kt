package com.example.projectct.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.InterfaceAPI.Common
import com.example.projectct.InterfaceAPI.RetrofitService
import com.example.projectct.R
import com.example.projectct.helpClass.Token
import com.example.projectct.helpClass.UserAuth
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Pair as UtilPair

class LoginActivity : AppCompatActivity() {
    lateinit var mService: RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Hooks
        val buttonLogin = findViewById<Button>(R.id.sign_in)
        val buttonRegister = findViewById<Button>(R.id.sign_up)
        val forgotPassword = findViewById<Button>(R.id.forgot)

            //OnClick
        buttonLogin.setOnClickListener(loginActivityListener)
        buttonRegister.setOnClickListener(registerActivityListener)
        forgotPassword.setOnClickListener(forgotPasswordAcitivityListener)
    }

    private val loginActivityListener = View.OnClickListener { loginAcitivity() }
    private val registerActivityListener = View.OnClickListener { registerActivity() }
    private val forgotPasswordAcitivityListener = View.OnClickListener { forgotAcitivity() }

    private fun registerActivity(){
        val intent = Intent(this, RegisterActivity::class.java)
        val image = findViewById<ImageView>(R.id.logoImage)
        val logo = findViewById<TextView>(R.id.logo_tag)
        val username = findViewById<TextInputLayout>(R.id.username)
        val password = findViewById<TextInputLayout>(R.id.password)
        val buttonLogin = findViewById<Button>(R.id.sign_in)
        val buttonRegister = findViewById<Button>(R.id.sign_up)
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            var options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                UtilPair.create(image, "logo_image"),
                UtilPair.create(logo, "logo_tag"),
                UtilPair.create(username, "logo_username"),
                UtilPair.create(password, "logo_password"),
                UtilPair.create(buttonLogin, "transition_button"),
                UtilPair.create(buttonRegister, "transition_button_2")
            )
            startActivity(intent, options.toBundle())
        }
    }

    private fun forgotAcitivity(){
        val intent = Intent(this, ForgotActivity::class.java)
        startActivity(intent)
    }

    private fun loginAcitivity(){
        val login = findViewById<EditText>(R.id.username_editText)
        val pass = findViewById<EditText>(R.id.password_editText)
        if(login.text.length>0 && pass.text.length>0){
            //TODO create file token?? Or how use it
            val intent = Intent(this, HomeActivity::class.java)
            mService = Common.retrofitService
            mService.login(UserAuth(login.text.toString(),pass.text.toString())).enqueue(object : Callback<Token>{
                override fun onResponse(call: Call<Token?>, response: Response<Token>) {
                  if(response.code()==401){
                      Toast.makeText(this@LoginActivity,R.string.errorLogin, Toast.LENGTH_SHORT).show()
                      login.text.clear()
                      pass.text.clear()
                  }
                    else {
                      startActivity(intent)
                  }
                }
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Toast.makeText(this@LoginActivity,R.string.errorInternetConnect, Toast.LENGTH_SHORT).show()
                    login.text.clear()
                    pass.text.clear()
                    t.printStackTrace()
                }
            })
        }
        else{
            Toast.makeText(this, R.string.errorRegClear, Toast.LENGTH_SHORT).show()
            login.text.clear()
            pass.text.clear()
        }
    }


}


