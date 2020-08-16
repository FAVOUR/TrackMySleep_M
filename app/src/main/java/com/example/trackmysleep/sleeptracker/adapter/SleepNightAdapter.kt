package com.example.trackmysleep.sleeptracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trackmysleep.R
import com.example.trackmysleep.database.SleepNight
import com.example.trackmysleep.databinding.ListItemSleepNightBinding
import java.lang.ClassCastException

class SleepNightAdapter(val sleepNightListener: SleepNightListener) : ListAdapter<DemoItem,RecyclerView.ViewHolder>(SleepNightDiffUtilCallback()) {
    private val ITEM_VIEW_TYPE_HEADER = 0
    private val ITEM_VIEW_TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  when(viewType){
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent = parent)
            ITEM_VIEW_TYPE_HEADER -> TextViewModel.from(parent = parent)

            else -> throw ClassCastException("Unable to cast class to ${viewType}")
        }
    }

    fun addHeaderAndSubmitList(list :List<SleepNight>?){


            val items =  when (list){
                null -> listOf(DemoItem.Header)
                else -> listOf(DemoItem.Header) + list.map { DemoItem.SleepNightItem(it) }
            }

        submitList(items)


    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)){
                  is DemoItem.Header ->ITEM_VIEW_TYPE_HEADER
                  is DemoItem.SleepNightItem ->ITEM_VIEW_TYPE_ITEM
                  else -> -1
        }


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is ViewHolder ->{
                var nightItem = getItem(position) as DemoItem.SleepNightItem
                holder.bind(nightItem.sleepNight,sleepNightListener)

            }
        }
    }


      class SleepNightDiffUtilCallback : DiffUtil.ItemCallback<DemoItem>(){
          override fun areItemsTheSame(oldItem: DemoItem, newItem: DemoItem): Boolean {
              return  oldItem.id == newItem.id
          }

          override fun areContentsTheSame(oldItem: DemoItem, newItem: DemoItem): Boolean {
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



    class TextViewModel (view : View):RecyclerView.ViewHolder(view){

        companion object{
            fun from(parent:ViewGroup):TextViewModel{

                var inflater = LayoutInflater.from(parent.context)

                var view =inflater.inflate(R.layout.header,parent,false)

                return TextViewModel(view)
            }

        }

    }

}





   class SleepNightListener( val  clickListener:(sleepId:Long)->Unit){
    fun onClick(sleepNight: SleepNight) = clickListener(sleepNight.nightId)
    }

   sealed class  DemoItem(){
    abstract val id:Long
    object  Header:DemoItem(){
        override val id = Long.MIN_VALUE
    }

    data class SleepNightItem(val sleepNight: SleepNight):DemoItem(){
        override val id:Long =sleepNight.nightId
    }
}