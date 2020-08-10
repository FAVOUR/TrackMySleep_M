package com.example.trackmysleep.sleeptracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trackmysleep.R
import com.example.trackmysleep.convertDurationToFormatted
import com.example.trackmysleep.convertNumericQualityToString
import com.example.trackmysleep.database.SleepNight

class SleepNightAdapter : ListAdapter<SleepNight,SleepNightAdapter.ViewHolder>(SleepNightDiffUtilCallback()) {
//    var data = listOf<SleepNight>()
//     set(value) {
//         field =value
//         notifyDataSetChanged()
//     }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

//    override fun getItemCount(): Int {
//       return  data.size
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }


      class SleepNightDiffUtilCallback : DiffUtil.ItemCallback<SleepNight>(){
          override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
              return  oldItem.nightId == newItem.nightId
          }

          override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
              return  oldItem == newItem
          }

      }
     class  ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

         fun bind(item: SleepNight) {
            val res = itemView.context.resources
            sleepLength.text =
                convertDurationToFormatted(item.startTimeMill, item.endTimeMill, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)

            qualityImage.setImageResource(
                when (item.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                }
            )
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)

                val view = inflater.inflate(R.layout.list_item_sleep_night, parent, false)

                return (ViewHolder(view))
            }
        }
    }



}