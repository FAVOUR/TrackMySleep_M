package com.example.trackmysleep.sleeptracker.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trackmysleep.R
import com.example.trackmysleep.convertDurationToFormatted
import com.example.trackmysleep.convertNumericQualityToString
import com.example.trackmysleep.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
    var data = listOf<SleepNight>()
     set(value) {
         field =value
         notifyDataSetChanged()
     }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepNightAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.list_item_sleep_night,parent,false)

          return (SleepNightAdapter.ViewHolder(view))
    }

    override fun getItemCount(): Int {
       return  data.size
    }

    override fun onBindViewHolder(holder: SleepNightAdapter.ViewHolder, position: Int) {
        val item = data[position]

        val res = holder.itemView.context.resources
        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMill, item.endTimeMill, res)
        holder.quality.text= convertNumericQualityToString(item.sleepQuality, res)

        holder.qualityImage.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }






    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
    }

}