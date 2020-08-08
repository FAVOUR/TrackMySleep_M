package com.example.trackmysleep.sleepquality

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Dao
import com.example.trackmysleep.database.SleepDataBaseDoa
import java.lang.IllegalArgumentException

class SleepQualityViewModelFactory(val sleepNight:Long, val sleepDataBaseDoa: SleepDataBaseDoa):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

      if( modelClass.isAssignableFrom(SleepQualityViewModel::class.java)){
           return SleepQualityViewModel(sleepNight,sleepDataBaseDoa) as T
        }
        throw IllegalArgumentException("ViewModel cannot be created")
    }


}