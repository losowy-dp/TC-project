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
import com.example.projectct.helpClass.Dane.DaneOrder
import android.util.Pair as UtilPair


class MainActivity : AppCompatActivity() {

    val SPLASH_SCREEN :Long = 3000
    //Variable
    lateinit var top_animation: Animation
    lateinit var bot_animation: Animation
    lateinit var image: ImageView
    lateinit var logo: TextView
    lateinit var tag: TextView
    private lateinit var daneOrder: DaneOrder

    override fun onCreate(savedInstanceState: Bundle?) {
        daneOrder = DaneOrder(this)
        daneOrder.clear()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Animation
        println("******************")
        println("New5")
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bot_animation = AnimationUtils.loadAnimation(this,R.anim.botom_animation)
        //Hooks
        image = findViewById(R.id.icon)
        logo = findViewById(R.id.textView)
        tag = findViewById(R.id.textView2)
        image.startAnimation(top_animation)
        logo.startAnimation(bot_animation)
        tag.startAnimation(bot_animation)
        //Handler
        Handler(Looper.getMainLooper()).postDelayed({
           val intent = Intent(this,LoginActivity::class.java)

            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
                val options = ActivityOptions.makeSceneTransitionAnimation(this,
                UtilPair.create(image,"logo_image"),
                UtilPair.create(logo,"logo_text"))
            startActivity(intent, options.toBundle())
            }
        },SPLASH_SCREEN)
    }
}