package com.example.projectct.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectct.R


/**
 * A simple [Fragment] subclass.
 * Use the [Add_order.newInstance] factory method to
 * create an instance of this fragment.
 */
class Add_order : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_order, container, false)
    }

}