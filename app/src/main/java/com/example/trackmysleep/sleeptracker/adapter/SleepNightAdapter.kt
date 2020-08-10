package com.example.trackmysleep.sleeptracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
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
     class  ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){

         fun bind(item: SleepNight) {
            binding.sleep =item
             binding.executePendingBindings()
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