package com.example.projectct.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.projectct.InterfaceAPI.NetworkAPI
import com.example.projectct.R
import com.example.projectct.helpClass.User
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import android.util.Pair as UtilPair
import kotlin.Int as Int

class RegisterActivity : AppCompatActivity() {
    var rootNode = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //Hooks
        val buttonRegister = findViewById<Button>(R.id.sign_up)
        val buttonBack = findViewById<Button>(R.id.back_login)
        //OnClick
        buttonRegister.setOnClickListener(registerActivityListener)
        buttonBack.setOnClickListener(backActivityListener)
    }

    private val registerActivityListener = View.OnClickListener { homeActivity() }

    private val backActivityListener = View.OnClickListener { loginActivity() }

    private fun homeActivity(){
        val username = findViewById<EditText>(R.id.reg_name_editText)
        val phone = findViewById<EditText>(R.id.phone_number_editText)
        val email  = findViewById<EditText>(R.id.email_editText)
        val password = findViewById<EditText>(R.id.passwordReg_editText)
        val pass_rep = findViewById<EditText>(R.id.password_repeat_editText)
        //TODO Add validate phone number must be 1
        if(username.text.length>0 && phone.text.length>0 && email.text.length>0 && password.text.length >0 && pass_rep.text.length >0 && password.text.toString() == pass_rep.text.toString() && CheckUser(email.text.toString())){
            //TODO JSON Request to Django Serwer
                //TODO add a edit text to Login and do responce Layout
                    //Work
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
           /* NetworkAPI.getInstance().jsonApi.getIDUser(User(username.text.toString(),password.text.toString(),username.text.toString(),phone.text.toString(),email.text.toString()))
                    .enqueue(object: retrofit2.Callback<Int> {
                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    var userID = response.body()
                    intent.putExtra("id",userID)

                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity,R.string.errorLogin, Toast.LENGTH_SHORT).show()
                    username.text.clear()
                    phone.text.clear()
                    email.text.clear()
                    password.text.clear()
                    pass_rep.text.clear()
                    t.printStackTrace()
                }
            })*/
        }
        else{
           username.text.clear()
            phone.text.clear()
            email.text.clear()
            password.text.clear()
            pass_rep.text.clear()
            Toast.makeText(this,R.string.errorLogin, Toast.LENGTH_SHORT).show()
        }
    }

    private fun CheckUser(toString: String): Boolean {
        var reference = rootNode.getReference("users")
        var checkUser = reference
        return false
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