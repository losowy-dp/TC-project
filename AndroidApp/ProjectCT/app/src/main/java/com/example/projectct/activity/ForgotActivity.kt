package com.example.projectct.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.projectct.R
import com.google.android.material.textfield.TextInputLayout

class ForgotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        val buttonSend = findViewById<Button>(R.id.button_send)
        //do listnera
        buttonSend.setOnClickListener(listnerSend)
    }
    private val listnerSend = View.OnClickListener { (sendEmail()) }
    private val listnerOk = View.OnClickListener { (ok()) }
    private fun sendEmail(){
        val buttonSend = findViewById<Button>(R.id.button_send)
        val buttonOk = findViewById<Button>(R.id.button_ok)
        buttonOk.setOnClickListener(listnerOk)
        buttonOk.visibility = View.VISIBLE
        buttonSend.visibility = View.INVISIBLE
        val editText = findViewById<TextInputLayout>(R.id.email_forgotView)
        editText.visibility = View.INVISIBLE
        val anim = AnimationUtils.loadAnimation(this,R.anim.anim_image)
        val imageSend = findViewById<ImageView>(R.id.image_email)
        val text =  findViewById<TextView>(R.id.forgot_textView)

        imageSend.startAnimation(anim)
        imageSend.visibility = View.VISIBLE
        text.visibility = View.INVISIBLE
    }
    private fun ok(){
        val intent = Intent(this, LoginActivity()::class.java)
        startActivity(intent)
    }
}