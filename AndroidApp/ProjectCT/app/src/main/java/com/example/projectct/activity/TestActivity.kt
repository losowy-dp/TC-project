package com.example.projectct.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.projectct.R
import com.squareup.picasso.Picasso

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        var imageView = findViewById<ImageView>(R.id.imageView2)

        Picasso.get().load("https://i.imgur.com/elyVhsP.jpg").resize(200,140).into(imageView)
    }
}