package com.example.projectct.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.projectct.Edit
import com.example.projectct.R

class Profile : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonEditProfile = view.findViewById<Button>(R.id.but_edit_profile)

        buttonEditProfile.setOnClickListener(buttonEditProfileListener)

        }
    private val buttonEditProfileListener = View.OnClickListener { editProfile() }

    private fun editProfile(){
        val intent = Intent (activity, Edit::class.java)
        startActivity(intent)
    }
}