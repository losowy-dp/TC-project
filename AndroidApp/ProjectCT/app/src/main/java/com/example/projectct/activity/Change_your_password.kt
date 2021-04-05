package com.example.projectct.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.R
import java.util.regex.Pattern


class Change_your_password : AppCompatActivity() {
    lateinit var oldpassword: EditText
    lateinit var newpassword: EditText
    lateinit var againpassword: EditText

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
         oldpassword = findViewById<EditText>(R.id.old_password_editText)
         newpassword = findViewById<EditText>(R.id.new_password_editText)
         againpassword = findViewById<EditText>(R.id.again_password_editText)
        if(errorCheck()){

        }
        /*val duration = Toast.LENGTH_LONG
        if(newpassword.text.length < 8) {
            val toast = Toast.makeText(this@Change_your_password, "R.string.errorNewpassSameOldpass", duration)
            toast.show()
        } else{
            if (oldpassword.text == newpassword.text) {
                val toast = Toast.makeText(this@Change_your_password, R.string.errorNewpassSameOldpass, duration)
                toast.show()
            }else if(newpassword.text != againpassword.text){
                val toast = Toast.makeText(this@Change_your_password, R.string.errorNewpassNotSameAgainpass, duration)
                toast.show()
            }else{

            }
        }*/
    }
    private fun errorCheck(): Boolean{
        if(newpassword.text.length < 8 && Pattern.matches("^[a-zA-Z0-9]+\$",newpassword.text.toString())==false){
            Toast.makeText(this,R.string.errorRegNotGoodPass,Toast.LENGTH_SHORT).show()
            return false
        }
        if(oldpassword.text.equals(newpassword.text)){
            Toast.makeText(this,R.string.errorNewpassSameOldpass,Toast.LENGTH_SHORT).show()
            return false
        }
        if(!newpassword.text.equals(againpassword.text)){
            Toast.makeText(this,R.string.errorNewpassNotSameAgainpass,Toast.LENGTH_SHORT).show()
            return false
        }
        //TODO: Check with database
        return true
    }
    private fun backToEditProfileListener(){
        val intent = Intent(this, Edit::class.java)
        startActivity(intent)
    }


}