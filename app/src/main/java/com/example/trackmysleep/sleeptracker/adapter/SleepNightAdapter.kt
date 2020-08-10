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
import com.example.trackmysleep.databinding.ListItemSleepNightBinding

class SleepNightAdapter : ListAdapter<SleepNight,SleepNightAdapter.ViewHolder>(SleepNightDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



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
     class  ViewHolder private constructor(binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){
        val sleepLength: TextView = binding.sleepLength
        val quality: TextView = binding.qualityString
        val qualityImage: ImageView = binding.qualityImage

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

                val binding = ListItemSleepNightBinding.inflate(inflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }



}