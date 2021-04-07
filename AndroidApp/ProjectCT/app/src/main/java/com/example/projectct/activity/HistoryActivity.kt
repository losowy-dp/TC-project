package com.example.projectct.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.R

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_order)

        val buttonBacktoMenu = findViewById<Button>(R.id.but_back_to_menu2)

        buttonBacktoMenu.setOnClickListener(buttonBacktoMenuListener)
    }

    private val buttonBacktoMenuListener = View.OnClickListener { BacktoMenu() }

    private fun BacktoMenu(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}