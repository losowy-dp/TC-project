package com.example.projectct.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.InterfaceAPI.ApiClient
import com.example.projectct.R
import com.example.projectct.helpClass.Transport.TransportationPrimary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Order : AppCompatActivity() {
    lateinit var apiClient: ApiClient
    lateinit var from: TextView
    lateinit var where: TextView
    lateinit var remuneration: TextView
    lateinit var description: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        fullFields(intent.extras!!.getString("id")!!)
        val buttonBack = findViewById<Button>(R.id.back)
        val buttonAccept = findViewById<Button>(R.id.accept)
        val buttonOwner = findViewById<Button>(R.id.account_order)
        buttonBack.setOnClickListener(buttonBackOnClickListener)
        buttonAccept.setOnClickListener(buttonAccpetOnClickListener)
        buttonOwner.setOnClickListener(buttonOwnerOnClickListener)
    }
    private val buttonBackOnClickListener = View.OnClickListener { backActivity() }
    private val buttonAccpetOnClickListener = View.OnClickListener { acceptActivity() }
    private val buttonOwnerOnClickListener = View.OnClickListener { ownerActivity() }

    private fun backActivity(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

    private fun acceptActivity(){
        //TODO: Accept
    }
    private fun ownerActivity(){
        //TODO: Owner Info
    }
    private fun fullFields(id: String){
        apiClient = ApiClient()
        apiClient.getApiService().takeTransport(id).enqueue(object: Callback<List<TransportationPrimary>>{
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                val telo = response.body()
                if (telo != null) {
                    telo.forEach {
                        from = findViewById(R.id.from_oreder)
                        where = findViewById(R.id.where_order)
                        remuneration = findViewById(R.id.renumeration_order)
                        description = findViewById(R.id.description_in_order)
                        from.text = it.start_location
                        where.text = it.delivery_location
                        remuneration.text = it.price + " " + it.currency
                        description.text = it.description
                    }
                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}