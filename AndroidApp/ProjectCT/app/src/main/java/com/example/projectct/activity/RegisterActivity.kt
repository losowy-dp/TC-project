package com.example.projectct.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.InterfaceAPI.ApiClient
import com.example.projectct.InterfaceAPI.SessionManager
import com.example.projectct.R
import com.example.projectct.helpClass.Token.Token
import com.example.projectct.helpClass.User.DaneUserToken
import com.example.projectct.helpClass.User.User
import com.example.projectct.helpClass.User.UserAuth
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern
import android.util.Pair as UtilPair

class RegisterActivity : AppCompatActivity() {
   lateinit var  fullname : EditText
   lateinit var  email : EditText
   lateinit var password : EditText
   lateinit var pass_rep: EditText
   lateinit var apiClient: ApiClient
   lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //Hooks
        val buttonRegister = findViewById<Button>(R.id.sign_up)
        val buttonBack = findViewById<Button>(R.id.back_login)
        //OnClick
        buttonRegister.setOnClickListener(registerActivityListener)
        buttonBack.setOnClickListener(backActivityListener)
        fullname = findViewById(R.id.reg_name_editText)
        email = findViewById(R.id.email_editText)
        password = findViewById(R.id.passwordReg_editText)
        pass_rep = findViewById(R.id.password_repeat_editText)
    }

    private val registerActivityListener = View.OnClickListener { homeActivity() }

    private val backActivityListener = View.OnClickListener { loginActivity() }

    private fun homeActivity(){
        if(CheckData()) {
            val intent = Intent(this, HomeActivity::class.java)
            apiClient = ApiClient()
            sessionManager = SessionManager(this)
            val arrayName: List<String> = fullname.text.toString().split(" ")
            apiClient.getApiService().register(User(password.text.toString(), email.text.toString(), email.text.toString(),arrayName[0],arrayName[1])).enqueue(object : Callback<DaneUserToken> {
                override fun onResponse(call: Call<DaneUserToken>, response: Response<DaneUserToken>) {
                    if (response.code() == 400) {
                        ClearData()
                        Toast.makeText(this@RegisterActivity, R.string.errorRegEmailIsUse, Toast.LENGTH_SHORT).show()
                    } else {
                        apiClient.getApiService().login(UserAuth(email.text.toString(), password.text.toString())).enqueue(object : Callback<Token> {
                            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                                val loginResponse = response.body()
                                sessionManager.saveAuthToken(loginResponse!!.authToken!!)
                                startActivity(intent)
                            }

                            override fun onFailure(call: Call<Token>, t: Throwable) {
                            }

                        })
                    }
                }

                override fun onFailure(call: Call<DaneUserToken>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, R.string.errorInternetConnect, Toast.LENGTH_SHORT).show()
                }
            })
        }
        else{
            ClearData()
        }
    }

    private fun ClearData(){
        email.text.clear()
        password.text.clear()
        pass_rep.text.clear()
        fullname.text.clear()
    }

    private fun CheckData(): Boolean {
        if (fullname.text.isEmpty() || email.text.isEmpty() || password.text.isEmpty() || pass_rep.text.isEmpty()) {
            Toast.makeText(this, R.string.errorRegClear, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Pattern.matches("^[a-zA-Z0-9]+\$", password.text.toString()) || password.text.length < 8 || !Pattern.matches(email.text.toString(), password.text.toString())) {
            Toast.makeText(this, R.string.errorRegNotGoodPass, Toast.LENGTH_SHORT).show()
            return false
        }
        if (!Pattern.matches(email.text.toString(), password.text.toString())){
            Toast.makeText(this, R.string.errorEmailInPass, Toast.LENGTH_SHORT).show()
        return false
        }
        if(password.text.toString() != pass_rep.text.toString()){
            Toast.makeText(this,R.string.errorRegPassNotRepeat,Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun loginActivity()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val intent = Intent(this, LoginActivity::class.java)
            val image = findViewById<ImageView>(R.id.logo_image)
            val tag = findViewById<TextView>(R.id.reg_tag)
            val password = findViewById<TextInputLayout>(R.id.password_repeat)
            val button = findViewById<Button>(R.id.sign_up)
            val button2 = findViewById<Button>(R.id.back_login)
            val option =
                    ActivityOptions.makeSceneTransitionAnimation(this,
                            UtilPair.create(image, "logo_image"),
                            UtilPair.create(tag, "logo_tag"),
                            UtilPair.create(password, "logo_password"),
                            UtilPair.create(button, "transition_button"),
                            UtilPair.create(button2, "transition_button_2"))
            startActivity(intent, option.toBundle())
        }
        else {
            startActivity(intent)
        }
    }
}