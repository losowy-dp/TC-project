package com.example.projectct.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projectct.R
import com.example.projectct.fragment.Add_order
import com.example.projectct.fragment.All_order_list
import com.example.projectct.fragment.My_order_list
import com.example.projectct.fragment.Profile
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), All_order_list.OnSelectedButtonListenerAll, My_order_list.OnSelectedButtonListenerMy {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //Hooks
        setCurentFragment(All_order_list())
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.itemIconTintList = null
        //OnClick
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.list_order -> setCurentFragment(All_order_list())
                R.id.add_order -> setCurentFragment(Add_order())
                R.id.profile -> setCurentFragment(Profile())
            }
            true
        }
    }

    private fun setCurentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
    //CallBack

    override fun onButtonSelectedAll(button: String) {
            supportFragmentManager.beginTransaction().apply { replace(R.id.fl_wrapper,My_order_list())
                commit()
            }
    }

    override fun onButtonSelectedMy(button: String) {
        supportFragmentManager.beginTransaction().apply { replace(R.id.fl_wrapper,All_order_list())
            commit()
        }
    }
}