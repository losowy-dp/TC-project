package com.example.projectct.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.projectct.InterfaceAPI.Common
import com.example.projectct.InterfaceAPI.RetrofitService
import com.example.projectct.R
import com.example.projectct.helpClass.CreateTransportations
import com.example.projectct.helpClass.TransportationPrimary
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


/**
 * A simple [Fragment] subclass.
 * Use the [Add_order.newInstance] factory method to
 * create an instance of this fragment.
 */
class Add_order : Fragment() {
    lateinit var spinner: Spinner
    lateinit var from: EditText
    lateinit var  where: EditText
    lateinit var price: EditText
    lateinit var desc: EditText
    lateinit var car: EditText
    lateinit var mService: RetrofitService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_order, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner = view.findViewById(R.id.renumertion_spinner)
        ArrayAdapter.createFromResource(activity!!, R.array.spinner_valut, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val button = view.findViewById<Button>(R.id.buttonAcceptAdd)
        button.setOnClickListener(buttonAcceptListener)
    }

    private var buttonAcceptListener = View.OnClickListener { buttonAccept() }
    private fun buttonAccept(){
        if(checkAllFields()){
            from = view!!.findViewById(R.id.from_edit_text)
            where = view!!.findViewById(R.id.where_edit_text)
            price = view!!.findViewById(R.id.renumeration_EditText)
            desc = view!!.findViewById(R.id.description_edit_text)
            car = view!!.findViewById(R.id.car_edit_text)
            spinner = view!!.findViewById(R.id.renumertion_spinner)
            mService = Common.retrofitService
            mService.createTransport(CreateTransportations(desc.text.toString(),price.text.toString(),spinner.selectedItem.toString(), /*activity!!.intent.getStringExtra("login").toString()*/1,from.text.toString(),where.text.toString()))
                    .enqueue(object:  retrofit2.Callback<TransportationPrimary> {
                        override fun onResponse(call: Call<TransportationPrimary>, response: Response<TransportationPrimary>) {
                           if(response.code()==201){
                               clearAllFields()
                               Toast.makeText(activity!!,R.string.addTrans,Toast.LENGTH_SHORT).show()
                           }
                            else{
                                clearAllFields()
                               Toast.makeText(activity!!,R.string.errorAddTrans,Toast.LENGTH_SHORT).show()
                           }
                        }

                        override fun onFailure(call: Call<TransportationPrimary>, t: Throwable) {
                            Toast.makeText(activity!!, R.string.errorInternetConnect, Toast.LENGTH_SHORT).show()
                        }

                    })
        }
    }
    private fun checkAllFields(): Boolean{
        from = view!!.findViewById(R.id.from_edit_text)
        where = view!!.findViewById(R.id.where_edit_text)
        price = view!!.findViewById(R.id.renumeration_EditText)
        desc = view!!.findViewById(R.id.description_edit_text)
        car = view!!.findViewById(R.id.car_edit_text)
        if(from.text.length>0 && where.text.length>0 && price.text.length>0 && desc.text.length>0 && car.text.length>0){
            return true
        }
        else{
            Toast.makeText(activity!!, R.string.errorRegClear, Toast.LENGTH_SHORT).show()
            clearAllFields()
            return false
        }
    }
    private fun clearAllFields(){
        from = view!!.findViewById(R.id.from_edit_text)
        where = view!!.findViewById(R.id.where_edit_text)
        price = view!!.findViewById(R.id.renumeration_EditText)
        desc = view!!.findViewById(R.id.description_edit_text)
        car = view!!.findViewById(R.id.car_edit_text)
        from.text.clear()
        where.text.clear()
        price.text.clear()
        desc.text.clear()
        car.text.clear()
    }
}