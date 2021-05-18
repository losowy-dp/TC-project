package com.example.projectct.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.projectct.InterfaceAPI.ApiClient
import com.example.projectct.InterfaceAPI.SessionManager
import com.example.projectct.R
import com.example.projectct.activity.MapActivity
import com.example.projectct.helpClass.Transport.CreateTransportations
import com.example.projectct.helpClass.Transport.TransportationPrimary
import com.example.projectct.helpClass.User.DaneUserToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [Add_order.newInstance] factory method to
 * create an instance of this fragment.
 */
class   Add_order : Fragment() {
    lateinit var spinner: Spinner
    lateinit var from: Button
    lateinit var  where:Button
    lateinit var from_edit: EditText
    lateinit var where_edit: EditText
    private var from_x: Int? = null
    private var from_y: Int? = null
    private var where_x: Int? = null
    private var where_y: Int? = null
    lateinit var price: EditText
    lateinit var desc: EditText
    lateinit var car: EditText
    lateinit var apiClient: ApiClient
    lateinit var sessionManager: SessionManager
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
       // checkDate()
        val button = view.findViewById<Button>(R.id.buttonAcceptAdd)
        button.setOnClickListener(buttonAcceptListener)
        val buttonFrom = view.findViewById<Button>(R.id.from)
        val buttonWhere = view.findViewById<Button>(R.id.where)
        buttonFrom.setOnClickListener(buttonFromListener)
        buttonWhere.setOnClickListener(buttonWhereListener)
    }

    private fun checkDate() {
        TODO("Not yet implemented")
    }

    private var buttonFromListener = View.OnClickListener { buttonMap("From") }
    private var buttonWhereListener = View.OnClickListener { buttonMap("Where") }
    private var buttonAcceptListener = View.OnClickListener { buttonAccept() }



    private fun buttonMap(name: String){
        val intent = Intent(activity, MapActivity::class.java)
        intent.putExtra("pos",name)
        startActivity(intent)
    }
    private fun buttonAccept(){
        if(checkAllFields()){
            apiClient = ApiClient()
            var id: String = ""
            sessionManager = SessionManager(activity!!)
            apiClient.getApiService().fetchDana(token = "Bearer ${sessionManager.fetchAuthToken()}").enqueue(object : retrofit2.Callback<DaneUserToken> {
                override fun onResponse(
                        call: Call<DaneUserToken>,
                        response: Response<DaneUserToken>
                ) {
                    val data = response.body()
                    if (response.code() != 401) {
                        id = data!!.id.toString()
                    } else {
                        println("Hello")
                    }
                }

                override fun onFailure(call: Call<DaneUserToken>, t: Throwable) {
                    Toast.makeText(activity, R.string.errorInternetConnect, Toast.LENGTH_SHORT).show()
                }
            })
            println("***************************************")
            println(id)
            println("***************************************")
            apiClient.getApiService().createTransport(CreateTransportations(desc.text.toString(),price.text.toString(),spinner.selectedItem.toString(),id, from.text.toString(),where.text.toString()))
                    .enqueue(object: Callback<TransportationPrimary>{
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
        from = view!!.findViewById(R.id.from)
        where = view!!.findViewById(R.id.where)
        price = view!!.findViewById(R.id.renumeration_EditText)
        desc = view!!.findViewById(R.id.description_edit_text)
        car = view!!.findViewById(R.id.car_edit_text)
        //Add new text Field from and Where
        if( price.text.isNotEmpty() && desc.text.isNotEmpty() && car.text.isNotEmpty()){
            return true
        }
        else{
            Toast.makeText(activity!!, R.string.errorRegClear, Toast.LENGTH_SHORT).show()
            clearAllFields()
            return false
        }
    }
    private fun clearAllFields(){
        price.text.clear()
        desc.text.clear()
        car.text.clear()
    }
}