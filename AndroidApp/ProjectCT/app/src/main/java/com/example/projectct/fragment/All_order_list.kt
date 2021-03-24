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
 * Use the [All_order_list.newInstance] factory method to
 * create an instance of this fragment.
 */
class All_order_list : Fragment() {
    interface OnSelectedButtonListenerAll{
        fun onButtonSelectedAll(button: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView  =  inflater.inflate(R.layout.fragment_all_order_list, container, false)

        return  rootView
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.all_order_fragment)
        button.backgroundTintList = null
        val buttonMyOrder = view.findViewById<Button>(R.id.my_order_fragment)
        buttonMyOrder.setOnClickListener(MyOrderFragment)
    }

    private val MyOrderFragment = View.OnClickListener { OrderFragment() }
    private fun OrderFragment(){
        val listener = activity as OnSelectedButtonListenerAll?
        listener?.onButtonSelectedAll("all_order")
    }
}