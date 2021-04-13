package com.example.projectct.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectct.activity.Edit


import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.projectct.InterfaceAPI.ApiClient
import com.example.projectct.InterfaceAPI.SessionManager
import com.example.projectct.R
import com.example.projectct.activity.HistoryActivity
import com.example.projectct.helpClass.User.DaneUserToken
import com.example.projectct.helpClass.User.UserPhone
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : Fragment() {
    lateinit var apiClient: ApiClient
    lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonEditProfile = view.findViewById<Button>(R.id.but_edit_profile)
        val buttonHistoryOrders = view.findViewById<Button>(R.id.but_history_orders)

        buttonEditProfile.setOnClickListener(buttonEditProfileListener)
        buttonHistoryOrders.setOnClickListener(buttonHistoryOrdersListener)
        filed(view)

        }
    private val buttonEditProfileListener = View.OnClickListener { editProfile() }
    private val buttonHistoryOrdersListener = View.OnClickListener { historyOrders() }
    private fun filed(view: View){
        var first_name = view.findViewById<TextView>(R.id.profile_first_name)
        var phone = view.findViewById<TextView>(R.id.profile_number_phone)
        var email = view.findViewById<TextView>(R.id.profile_email)
        apiClient = ApiClient()
        sessionManager = SessionManager(activity!!)
        apiClient.getApiService().fetchDana(token = "Bearer ${sessionManager.fetchAuthToken()}")
            .enqueue(object : retrofit2.Callback<DaneUserToken> {
                override fun onResponse(
                    call: Call<DaneUserToken>,
                    response: Response<DaneUserToken>
                ) {
                    var data = response.body()
                    if (response.code() != 401) {
                        apiClient.getApiService().takeInfoPrimitive(data!!.id).enqueue(
                            object : Callback<List<UserPhone>> {

                                override fun onFailure(call: Call<List<UserPhone>>, t: Throwable) {
                                    Toast.makeText(
                                        activity!!,
                                        "Nowy2",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                override fun onResponse(
                                    call: Call<List<UserPhone>>,
                                    response: Response<List<UserPhone>>
                                ) {
                                    var dane = response.body()
                                    if (response.code() == 200) {
                                        dane!!.forEach {
                                            first_name.setText(it.user!!.first_name)
                                            phone.setText(it.number_of_phone)
                                            email.setText(it.user!!.email)
                                        }
                                    } else {
                                        Toast.makeText(
                                            activity!!,
                                            "Nowy",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                            })
                    } else {
                        Toast.makeText(
                            activity!!,
                            R.string.errorLogin,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<DaneUserToken>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    private fun editProfile(){
        val intent = Intent (activity, Edit::class.java)
        startActivity(intent)
    }

    private fun historyOrders(){
        val intent = Intent (activity, HistoryActivity::class.java)
        startActivity(intent)
    }
}