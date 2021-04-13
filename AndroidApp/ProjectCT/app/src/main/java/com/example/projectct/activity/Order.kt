package com.example.projectct.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.InterfaceAPI.Common
import com.example.projectct.InterfaceAPI.RetrofitService
import com.example.projectct.R
import com.example.projectct.helpClass.TransportationPrimary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Order : AppCompatActivity() {
    lateinit var mService: RetrofitService
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
        mService = Common.retrofitService
        mService.takeTransport(id).enqueue(object : Callback<List<TransportationPrimary>> {
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                var telo = response.body()
                if (telo != null) {
                    telo.forEach {
                        from = findViewById<TextView>(R.id.from_oreder)
                        where = findViewById<TextView>(R.id.where_order)
                        remuneration = findViewById<TextView>(R.id.renumeration_order)
                        description = findViewById<TextView>(R.id.description_in_order)
                        from.setText(it.start_location)
                        where.setText(it.delivery_location)
                        remuneration.setText(it.price + " " + it.currency)
                        description.setText(it.description)
                    }
                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}