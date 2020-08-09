package com.example.trackmysleep.sleeptracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trackmysleep.R
import com.example.trackmysleep.TextItemViewHolder
import com.example.trackmysleep.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<SleepNight>()
     set(value) {
         field =value
         notifyDataSetChanged()
     }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.text_item_view,parent,false)as TextView

          return (TextItemViewHolder(view))
    }

    override fun getItemCount(): Int {
       return  data.size
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {

        holder.textView.text = data[position].sleepQuality.toString()
    }

}