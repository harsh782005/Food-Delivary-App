package com.harsh.fooddelivaryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentAdapter(var studentList: ArrayList<StudentAdapterDataClass>) : BaseAdapter() {
    override fun getCount(): Int {
        return studentList.size
    }

    override fun getItem(position: Int): Any {
        return studentList[position]
    }

    override fun getItemId(position: Int): Long {
        return studentList[position].etQnty?.toLong() ?: 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_base_adapter,
                parent, false
            )
        val etItem = view.findViewById<TextView>(R.id.etItem)
        var etQnty = view.findViewById<TextView>(R.id.etQnty)
        etItem.setText(studentList[position].etItem.toString())
        etQnty.setText(studentList[position].etQnty.toString())
        return view
    }
}