package com.example.trackmysleep.sleeptracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trackmysleep.database.SleepNight
import com.example.trackmysleep.databinding.ListItemSleepNightBinding

class SleepNightAdapter(val sleepNightListener: SleepNightListener) : ListAdapter<SleepNight,SleepNightAdapter.ViewHolder>(SleepNightDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item,sleepNightListener)
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

         fun bind(
             item: SleepNight, sleepNightListener: SleepNightListener) {
            binding.sleep =item
             binding.executePendingBindings()
             binding.sleepListener =sleepNightListener

         }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)

                val binding = ListItemSleepNightBinding.inflate(inflater, parent, false)


                return ViewHolder(binding)
            }
        }
    }

    class SleepNightListener( val  clickListener:(sleepId:Long)->Unit){
        fun onClick(sleepNight: SleepNight) = clickListener(sleepNight.nightId)
    }

}