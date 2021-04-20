package com.example.projectct.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.InterfaceAPI.ApiClient
import com.example.projectct.InterfaceAPI.Common
import com.example.projectct.R
import com.example.projectct.helpClass.Transport.TransportationPrimary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryActivity : AppCompatActivity() {
    lateinit var arrayList: ArrayList<HashMap<String, String>>
    lateinit var map: HashMap<String,String>
    lateinit var apiClient: ApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_order)

        val buttonBacktoMenu = findViewById<Button>(R.id.but_back_to_menu2)

        buttonBacktoMenu.setOnClickListener(buttonBacktoMenuListener)
        var id = intent.extras!!.getString("idi").toString()
        println("****************************************")
        println(id)
        println("****************************************")
        var listView = findViewById<ListView>(R.id.historyOrderList)
        apiClient = ApiClient()
        apiClient.getApiService().historyOrder(id).enqueue(object: Callback<List<TransportationPrimary>>{
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
               var telo = response.body()
                if(telo!=null){
                    arrayList = ArrayList()
                    telo.forEach{
                        map = HashMap()
                        var nowa: String = it.start_location+ " --> " + it.delivery_location + "\n" + it.price + " " + it.currency
                        map.put("citys",nowa)
                        var  price: String  = it.id
                        map.put("value", price)
                        arrayList.add(map)
                    }
                    val adapter = SimpleAdapter(
                            this@HistoryActivity,
                            arrayList,
                            android.R.layout.simple_list_item_1,
                            arrayOf("citys"),
                            intArrayOf(android.R.id.text1)
                    )
                    listView.adapter = adapter
                    listView.onItemClickListener = object : AdapterView.OnItemClickListener{
                        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            val intent = Intent(this@HistoryActivity, Order::class.java)
                            intent.putExtra("id", arrayList.get(position).get("value"))
                            startActivity(intent)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
            }

        })
    }

    private val buttonBacktoMenuListener = View.OnClickListener { BacktoMenu() }

    private fun BacktoMenu(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}