package com.example.projectct.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.projectct.InterfaceAPI.Common
import com.example.projectct.InterfaceAPI.RetrofitService
import com.example.projectct.R
import com.example.projectct.helpClass.TransportationPrimary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [All_order_list.newInstance] factory method to
 * create an instance of this fragment.
 */
class All_order_list : Fragment() {
    lateinit var mService: RetrofitService
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
        var listView = view.findViewById<ListView>(R.id.allOrderList)
        mService = Common.retrofitService
        mService.getAll().enqueue(object : Callback<List<TransportationPrimary>> {
            override fun onResponse(call: Call<List<TransportationPrimary>>, response: Response<List<TransportationPrimary>>) {
                if (response.code() == 500) {
                    Toast.makeText(activity!!, "Error 500", Toast.LENGTH_SHORT).show()
                } else {
                    var telo = response.body();
                    if (telo != null) {
                        val arrayList: ArrayList<HashMap<String,String>> = ArrayList()
                        var map: HashMap<String,String>
                        telo.forEach {
                            map = HashMap()
                            var nowa: String = it.start_location+"-->"+it.delivery_location
                            map.put("citys",nowa)
                            var price: String = it.price+" "+it.currency
                            map.put("value",price)
                            arrayList.add(map)
                        }
                        val adapter = SimpleAdapter(
                                activity,
                                arrayList,
                                android.R.layout.simple_list_item_2,
                                arrayOf("citys", "value"),
                                intArrayOf(android.R.id.text1, android.R.id.text2)
                        )
                        listView.adapter = adapter
                        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
                            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long)
                            {
                                //remove toast and add pass a new activity
                                Toast.makeText(activity!!, "on klick", Toast.LENGTH_SHORT).show()
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