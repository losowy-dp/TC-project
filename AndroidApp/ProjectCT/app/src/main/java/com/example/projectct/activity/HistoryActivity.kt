package com.example.projectct.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectct.InterfaceAPI.ApiClient
import com.example.projectct.R
import com.example.projectct.helpClass.Transport.TransportationPrimary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryActivity : AppCompatActivity() {
    lateinit var arrayList: ArrayList<HashMap<String, String>>
    lateinit var map: HashMap<String,String>
    lateinit var apiClient: ApiClient
    lateinit var id: String
    lateinit var listView: ListView
    lateinit var spinner: Spinner
    lateinit var buttonAccept: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_order)

        val buttonBacktoMenu = findViewById<Button>(R.id.but_back_to_menu2)
        spinner = findViewById(R.id.spinnerFiltrHistory)
        ArrayAdapter.createFromResource(this, R.array.spinner_filtr, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        buttonAccept = findViewById(R.id.buttonFiltrHistory)
        buttonAccept.setOnClickListener(buttonAcceptFiltListener)
        buttonBacktoMenu.setOnClickListener(buttonBacktoMenuListener)
        id = intent.extras!!.getString("id").toString()
        listView = findViewById<ListView>(R.id.historyOrderList)
        historyDateNew()

    }
    private var buttonAcceptFiltListener = View.OnClickListener { AcceptFiltr() }
    private fun AcceptFiltr(){
        listView.adapter= null
        when (spinner.getSelectedItem().toString()) {
            "From the new ones" -> historyDateNew()
            "From the old ones" -> historyDateOld()
            "Falling price" -> historyPriceF()
            "Increasing price" -> historyPriceI()
        }
    }
    private fun historyDateOld(){
        apiClient = ApiClient()
        apiClient.getApiService().historyOrderSort(id).enqueue(object: Callback<List<TransportationPrimary>>{
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                val telo = response.body()
                if(telo!=null){
                    arrayList = ArrayList()
                    telo.forEach{
                        map = HashMap()
                        val split = it.data_created?.split("T")
                        val nowa: String = it.start_location+ " --> " + it.delivery_location + "\n Date: " + split!![0]
                        map["citys"] = nowa
                        val  price: String  = it.id
                        map["value"] = price
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
                    listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                        val intent = Intent(this@HistoryActivity, Order::class.java)
                        intent.putExtra("id", arrayList[position]["value"])
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
            }

        })
    }
    private fun historyPriceF(){
        apiClient = ApiClient()
        apiClient.getApiService().historyOrderSortPriceF(id).enqueue(object: Callback<List<TransportationPrimary>>{
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                val telo = response.body()
                if(telo!=null){
                    arrayList = ArrayList()
                    telo.forEach{
                        map = HashMap()
                        val split = it.data_created?.split("T")
                        val nowa: String = it.start_location+ " --> " + it.delivery_location + "\n Date: " + split!![0]
                        map["citys"] = nowa
                        val  price: String  = it.id
                        map["value"] = price
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
                    listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                        val intent = Intent(this@HistoryActivity, Order::class.java)
                        intent.putExtra("id", arrayList[position]["value"])
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
            }

        })
    }
    private fun historyPriceI(){
        apiClient = ApiClient()
        apiClient.getApiService().historyOrderSortPriceI(id).enqueue(object: Callback<List<TransportationPrimary>>{
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                val telo = response.body()
                if(telo!=null){
                    arrayList = ArrayList()
                    telo.forEach{
                        map = HashMap()
                        val split = it.data_created?.split("T")
                        val nowa: String = it.start_location+ " --> " + it.delivery_location + "\n Date: " + split!![0]
                        map["citys"] = nowa
                        val  price: String  = it.id
                        map["value"] = price
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
                    listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                        val intent = Intent(this@HistoryActivity, Order::class.java)
                        intent.putExtra("id", arrayList[position]["value"])
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
            }

        })
    }
    private fun historyDateNew(){
        apiClient = ApiClient()
        apiClient.getApiService().historyOrder(id).enqueue(object: Callback<List<TransportationPrimary>>{
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                val telo = response.body()
                if(telo!=null){
                    arrayList = ArrayList()
                    telo.forEach{
                        map = HashMap()
                        val split = it.data_created?.split("T")
                        val nowa: String = it.start_location+ " --> " + it.delivery_location + "\n Date: " + split!![0]
                        map["citys"] = nowa
                        val  price: String  = it.id
                        map["value"] = price
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
                    listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                        val intent = Intent(this@HistoryActivity, Order::class.java)
                        intent.putExtra("id", arrayList[position]["value"])
                        startActivity(intent)
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
        intent.putExtra("id", id)
        startActivity(intent)
    }

}