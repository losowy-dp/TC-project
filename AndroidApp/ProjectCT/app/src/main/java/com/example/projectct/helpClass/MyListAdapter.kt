package com.example.projectct.helpClass

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.projectct.R
import com.squareup.picasso.Picasso
import java.text.FieldPosition

class MyListAdapter(private val context: Activity, private val list: ArrayList<HashMap<String, String>>,private val title: Array<String>): ArrayAdapter<String>(context,R.layout.custom_list,title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View{
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list,null,true)

        val titleText = rowView.findViewById<TextView>(R.id.title_list)
        val imageView = rowView.findViewById<ImageView>(R.id.icon_list)
        println(title[position])
        titleText.text = list[position]["citys"];
        Picasso.get().load(title[position]).resize(150,100).into(imageView)
        return rowView
    }
}