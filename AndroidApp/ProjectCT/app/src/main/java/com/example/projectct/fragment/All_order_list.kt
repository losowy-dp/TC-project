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
import com.example.projectct.R
import com.example.projectct.activity.Order
import com.example.projectct.helpClass.MyListAdapter
import com.example.projectct.helpClass.Transport.TransportationPrimary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [All_order_list.newInstance] factory method to
 * create an instance of this fragment.
 */
class All_order_list : Fragment() {
    lateinit var apiClient: ApiClient
    lateinit var spinner: Spinner
    lateinit var buttonFiltr: Button
    lateinit var listView: ListView
    //Interface
    interface OnSelectedButtonListenerAll{
        fun onButtonSelectedAll(button: String)
    }
    //Lateinit
    lateinit var arrayList: ArrayList<HashMap<String, String>>
    lateinit var map: HashMap<String, String>

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
        /* SPINER FILTR */
        spinner = view.findViewById(R.id.spinnerFiltr)
        ArrayAdapter.createFromResource(activity!!, R.array.spinner_filtr, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        buttonFiltr = view.findViewById(R.id.buttonFiltr)
        buttonFiltr.setOnClickListener(buttonfiltrListener)
        val button = view.findViewById<Button>(R.id.all_order_fragment)
        button.backgroundTintList = null
        val buttonMyOrder = view.findViewById<Button>(R.id.my_order_fragment)
        buttonMyOrder.setOnClickListener(MyOrderFragment)
        listView = view.findViewById<ListView>(R.id.allOrderList)
        apiClient = ApiClient()
        apiClient.getApiService().getAll().enqueue(object : Callback<List<TransportationPrimary>> {
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                if (response.code() == 500) {
                    Toast.makeText(activity!!, "Error 500", Toast.LENGTH_SHORT).show()
                } else {
                    var title = arrayOf<String>()
                    val telo = response.body()
                    if (telo != null) {
                        arrayList = ArrayList()
                        telo.forEach {
                            map = HashMap()
                            val nowa: String = it.start_location!!.split(",")[0] + " --> " + it.delivery_location!!.split(",")[0] + "\n" + it.price + " " + it.currency
                            map.put("citys", nowa)
                            val price: String = it.id
                            map.put("value", price)
                            arrayList.add(map)
                            title+=it.photo
                        }
                        val adapter = SimpleAdapter(
                                activity,
                                arrayList,
                                android.R.layout.simple_list_item_1,
                                arrayOf("citys"),
                                intArrayOf(android.R.id.text1)
                        )
                        val myListAdapter = MyListAdapter(
                            activity!!,arrayList,title
                        )
                        listView.adapter = myListAdapter
                        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                val intent = Intent(activity!!, Order::class.java)
                                intent.putExtra("id", arrayList.get(position).get("value"))
                                startActivity(intent)
                            }
                        }
                    }

                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
                Toast.makeText(activity!!, "Failed Connections", Toast.LENGTH_SHORT).show()
            }
        })

    }
    private var buttonfiltrListener = View.OnClickListener { FilteApply() }
    private fun FilteApply() {
        listView.adapter= null
        when (spinner.getSelectedItem().toString()) {
            "From the new ones" -> sortNew()
            "From the old ones" -> sortOld()
            "Falling price" -> sortPriceF()
            "Increasing price" -> sortPriceI()
        }
    }

    private fun sortNew(){
        apiClient = ApiClient()
        apiClient.getApiService().getAll().enqueue(object : Callback<List<TransportationPrimary>> {
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                if (response.code() == 500) {
                    Toast.makeText(activity!!, "Error 500", Toast.LENGTH_SHORT).show()
                } else {
                    var title = arrayOf<String>()
                    val telo = response.body()
                    if (telo != null) {
                        arrayList = ArrayList()
                        telo.forEach {
                            map = HashMap()
                            val nowa: String = it.start_location!!.split(",")[0] + " --> " + it.delivery_location!!.split(",")[0] + "\n" + it.price + " " + it.currency
                            map.put("citys", nowa)
                            val price: String = it.id
                            map.put("value", price)
                            arrayList.add(map)
                            title += it.photo
                        }
                        val adapter = SimpleAdapter(
                                activity,
                                arrayList,
                                android.R.layout.simple_list_item_1,
                                arrayOf("citys"),
                                intArrayOf(android.R.id.text1)
                        )
                        val myListAdapter = MyListAdapter(
                                activity!!, arrayList, title
                        )
                        listView.adapter = myListAdapter
                        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                val intent = Intent(activity!!, Order::class.java)
                                intent.putExtra("id", arrayList.get(position).get("value"))
                                startActivity(intent)
                            }
                        }
                    }

                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
                Toast.makeText(activity!!, "Failed Connections", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun sortOld(){
        apiClient = ApiClient()
        apiClient.getApiService().getAllSortOld().enqueue(object : Callback<List<TransportationPrimary>> {
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                if (response.code() == 500) {
                    Toast.makeText(activity!!, "Error 500", Toast.LENGTH_SHORT).show()
                } else {
                    var title = arrayOf<String>()
                    val telo = response.body()
                    if (telo != null) {
                        arrayList = ArrayList()
                        telo.forEach {
                            map = HashMap()
                            val nowa: String = it.start_location!!.split(",")[0] + " --> " + it.delivery_location!!.split(",")[0] + "\n" + it.price + " " + it.currency
                            map.put("citys", nowa)
                            val price: String = it.id
                            map.put("value", price)
                            arrayList.add(map)
                            title+=it.photo
                        }
                        val adapter = SimpleAdapter(
                                activity,
                                arrayList,
                                android.R.layout.simple_list_item_1,
                                arrayOf("citys"),
                                intArrayOf(android.R.id.text1)
                        )
                        val myListAdapter = MyListAdapter(
                                activity!!,arrayList,title
                        )
                        listView.adapter = myListAdapter
                        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                val intent = Intent(activity!!, Order::class.java)
                                intent.putExtra("id", arrayList.get(position).get("value"))
                                startActivity(intent)
                            }
                        }
                    }

                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
                Toast.makeText(activity!!, "Failed Connections", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun sortPriceF(){
        apiClient = ApiClient()
        apiClient.getApiService().getAllSortPriceF().enqueue(object : Callback<List<TransportationPrimary>> {
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                if (response.code() == 500) {
                    Toast.makeText(activity!!, "Error 500", Toast.LENGTH_SHORT).show()
                } else {
                    var title = arrayOf<String>()
                    val telo = response.body()
                    if (telo != null) {
                        arrayList = ArrayList()
                        telo.forEach {
                            map = HashMap()
                            val nowa: String = it.start_location!!.split(",")[0] + " --> " + it.delivery_location!!.split(",")[0] + "\n" + it.price + " " + it.currency
                            map.put("citys", nowa)
                            val price: String = it.id
                            map.put("value", price)
                            arrayList.add(map)
                            title+=it.photo
                        }
                        val adapter = SimpleAdapter(
                                activity,
                                arrayList,
                                android.R.layout.simple_list_item_1,
                                arrayOf("citys"),
                                intArrayOf(android.R.id.text1)
                        )
                        val myListAdapter = MyListAdapter(
                                activity!!,arrayList,title
                        )
                        listView.adapter = myListAdapter
                        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                val intent = Intent(activity!!, Order::class.java)
                                intent.putExtra("id", arrayList.get(position).get("value"))
                                startActivity(intent)
                            }
                        }
                    }

                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
                Toast.makeText(activity!!, "Failed Connections", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun sortPriceI(){
        apiClient = ApiClient()
        apiClient.getApiService().getAllSortPriceI().enqueue(object : Callback<List<TransportationPrimary>> {
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                if (response.code() == 500) {
                    Toast.makeText(activity!!, "Error 500", Toast.LENGTH_SHORT).show()
                } else {
                    var title = arrayOf<String>()
                    val telo = response.body()
                    if (telo != null) {
                        arrayList = ArrayList()
                        telo.forEach {
                            map = HashMap()
                            val nowa: String = it.start_location!!.split(",")[0] + " --> " + it.delivery_location!!.split(",")[0] + "\n" + it.price + " " + it.currency
                            map.put("citys", nowa)
                            val price: String = it.id
                            map.put("value", price)
                            arrayList.add(map)
                            title+=it.photo
                        }
                        val adapter = SimpleAdapter(
                                activity,
                                arrayList,
                                android.R.layout.simple_list_item_1,
                                arrayOf("citys"),
                                intArrayOf(android.R.id.text1)
                        )
                        val myListAdapter = MyListAdapter(
                                activity!!,arrayList,title
                        )
                        listView.adapter = myListAdapter
                        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                val intent = Intent(activity!!, Order::class.java)
                                intent.putExtra("id", arrayList.get(position).get("value"))
                                startActivity(intent)
                            }
                        }
                    }

                }
            }

            override fun onFailure(call: Call<List<TransportationPrimary>>, t: Throwable) {
                Toast.makeText(activity!!, "Failed Connections", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private val MyOrderFragment = View.OnClickListener { OrderFragment() }
    private fun OrderFragment(){
        val listener = activity as OnSelectedButtonListenerAll?
        listener?.onButtonSelectedAll("all_order")
    }

}