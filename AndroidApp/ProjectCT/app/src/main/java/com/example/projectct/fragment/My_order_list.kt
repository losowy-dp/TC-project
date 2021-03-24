package com.example.projectct.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.projectct.R



/**
 * A simple [Fragment] subclass.
 * Use the [My_order_list.newInstance] factory method to
 * create an instance of this fragment.
 */
class My_order_list : Fragment() {
    //Interface
    interface OnSelectedButtonListenerMy{
        fun onButtonSelectedMy(button: String)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView  =  inflater.inflate(R.layout.fragment_my_order_list, container, false)

        return  rootView
    }
    private val MyOrderFragment = View.OnClickListener { OrderFragment() }
    private fun OrderFragment(){
        val listener = activity as My_order_list.OnSelectedButtonListenerMy?
        listener?.onButtonSelectedMy("my_order")
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonOrder = view.findViewById<Button>(R.id.my_order_my)
        buttonOrder.backgroundTintList = null
        val buttonMyOrder = view.findViewById<Button>(R.id.all_order_my)
        buttonMyOrder.setOnClickListener(MyOrderFragment)
    }}