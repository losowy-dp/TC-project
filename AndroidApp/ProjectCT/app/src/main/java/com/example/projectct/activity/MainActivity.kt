package com.example.projectct.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.R
import android.util.Pair as UtilPair


class MainActivity : AppCompatActivity() {

    val SPLASH_SCREEN :Long = 3000
    //Variable
    lateinit var top_animation: Animation
    lateinit var bot_animation: Animation
    lateinit var image: ImageView
    lateinit var logo: TextView
    lateinit var tag: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Animation
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bot_animation = AnimationUtils.loadAnimation(this,R.anim.botom_animation)
        //Hooks
        image = findViewById<ImageView>(R.id.icon)
        logo = findViewById<TextView>(R.id.textView)
        tag = findViewById<TextView>(R.id.textView2)
        image.startAnimation(top_animation)
        logo.startAnimation(bot_animation)
        tag.startAnimation(bot_animation)
        //Handler
        Handler(Looper.getMainLooper()).postDelayed({
           var intent = Intent(this,LoginActivity::class.java)

            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                var options = ActivityOptions.makeSceneTransitionAnimation(this,
                UtilPair.create(image,"logo_image"),
                UtilPair.create(logo,"logo_text"))
            startActivity(intent, options.toBundle())
            }
        },SPLASH_SCREEN)
    }
}