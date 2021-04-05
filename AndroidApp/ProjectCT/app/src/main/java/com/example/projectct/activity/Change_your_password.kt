package com.example.projectct.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.R


class Change_your_password : AppCompatActivity() {
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
        val oldpassword = findViewById<EditText>(R.id.old_password_editText)
        val newpassword = findViewById<EditText>(R.id.new_password_editText)
        val againpassword = findViewById<EditText>(R.id.again_password_editText)
        //TODO: Винести if відільною функцією/скоротити код тоаста в 1 лінію/duration не нужна змінна/запрос бази на провірку/регулярне вираження для пароля
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

    private fun backToEditProfileListener(){
        val intent = Intent(this, Edit::class.java)
        startActivity(intent)
    }


}