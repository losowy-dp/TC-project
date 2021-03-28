package com.example.projectct.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.projectct.InterfaceAPI.Common
import com.example.projectct.R
import com.example.projectct.helpClass.Token
import com.example.projectct.helpClass.User
import com.example.projectct.helpClass.UserAuth
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Response
import java.util.regex.Pattern
import javax.security.auth.callback.Callback
import android.util.Pair as UtilPair

class RegisterActivity : AppCompatActivity() {
   lateinit var username : EditText
   lateinit var  fullname : EditText
   lateinit var  email : EditText
   lateinit var password : EditText
   lateinit var pass_rep: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //Hooks
        val buttonRegister = findViewById<Button>(R.id.sign_up)
        val buttonBack = findViewById<Button>(R.id.back_login)
        //OnClick
        buttonRegister.setOnClickListener(registerActivityListener)
        buttonBack.setOnClickListener(backActivityListener)
        username = findViewById<EditText>(R.id.username_number_editText)
        fullname = findViewById<EditText>(R.id.reg_name_editText)
        email = findViewById<EditText>(R.id.email_editText)
        password = findViewById<EditText>(R.id.passwordReg_editText)
        pass_rep = findViewById<EditText>(R.id.password_repeat_editText)
    }

    private val registerActivityListener = View.OnClickListener { homeActivity() }

    private val backActivityListener = View.OnClickListener { loginActivity() }

    private fun homeActivity(){
        //TODO Add validate phone number must be 1
        if(CheckData()){
            //TODO JSON Request to Django Serwer
                //TODO add a edit text to Login and do responce Layout

            val intent = Intent(this, HomeActivity::class.java)
            var mService = Common.retrofitService
            mService.register(User(password.text.toString(),username.text.toString(),email.text.toString())).enqueue(object : retrofit2.Callback<Token> {
                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    if(response.code()==400){
                        ClearData()
                        Toast.makeText(this@RegisterActivity,R.string.errorRegEmailIsUse, Toast.LENGTH_SHORT).show()
                    }
                    else{
                            mService.login(UserAuth(username.text.toString(),password.text.toString())).enqueue(object: retrofit2.Callback<Token>{
                                override fun onResponse(
                                    call: Call<Token>,
                                    response: Response<Token>
                                ) {
                                   if(response.code()==401){
                                       ClearData()
                                       Toast.makeText(this@RegisterActivity,R.string.errorInternetConnect, Toast.LENGTH_SHORT).show()
                                   }
                                    else{
                                       startActivity(intent)
                                   }
                                }

                                override fun onFailure(call: Call<Token>, t: Throwable) {
                                    Toast.makeText(this@RegisterActivity,R.string.errorInternetConnect, Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    }
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    ClearData()
                    Toast.makeText(this@RegisterActivity,R.string.errorInternetConnect, Toast.LENGTH_SHORT).show()
                }
            })
        }
        else{
            ClearData()
        }
    }

    private fun ClearData(){
        username.text.clear()
        email.text.clear()
        password.text.clear()
        pass_rep.text.clear()
        fullname.text.clear()
    }

    private fun CheckData(): Boolean{
        if(username.text.length>0 && fullname.text.length>0 && email.text.length>0 && password.text.length >0 && pass_rep.text.length >0){
            Toast.makeText(this,R.string.errorRegClear,Toast.LENGTH_SHORT).show()
            return false
        }
        var regex = "^[a-zA-Z0-9]+$"
        if(Pattern.matches("^[a-zA-Z0-9]+\$",password.text.toString())==false || password.text.length<8){
            Toast.makeText(this,R.string.errorRegNotGoodPass,Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.text.toString() == pass_rep.text.toString()){
            Toast.makeText(this,R.string.errorRegPassNotRepeat,Toast.LENGTH_SHORT).show()
            return false
        }
        return true;
    }

    private fun loginActivity()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val intent = Intent(this, LoginActivity::class.java)
            val image = findViewById<ImageView>(R.id.logo_image)
            val tag = findViewById<TextView>(R.id.reg_tag)
            val username = findViewById<TextInputLayout>(R.id.reg_name)
            val password = findViewById<TextInputLayout>(R.id.password_repeat)
            val button = findViewById<Button>(R.id.sign_up)
            val button2 = findViewById<Button>(R.id.back_login)
            val option =
                    ActivityOptions.makeSceneTransitionAnimation(this,
                            UtilPair.create(image, "logo_image"),
                            UtilPair.create(tag, "logo_tag"),
                            UtilPair.create(username, "logo_username"),
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