package com.example.project_g07.adapter

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.project_g07.R
import com.example.project_g07.model.Course

class ListAdapter (context: Context, list: List<Course>) :
    ArrayAdapter<Course>(context, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_items, parent, false)

        val code = view.findViewById<TextView>(R.id.tvCode)
        val name = view.findViewById<TextView>(R.id.tvName)
        val detail = view.findViewById<TextView>(R.id.tvDetail)
        val completeImage = view.findViewById<ImageView>(R.id.imgComplete)
        val c = getItem(position)
        c?.let {
            code.text = it.code.toString()
            name.text = it.course
            detail.text = it.description
            val img = if(it.isCompleted) R.drawable.baseline_star_24 else R.drawable.baseline_star_outline_24
            completeImage.setImageResource(img)
        }

        return view
    }
}