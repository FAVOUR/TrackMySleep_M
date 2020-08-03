package com.example.trackmysleep.sleeptracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.trackmysleep.database.SleepDataBaseDoa

class SleepTrackerViewModelFactory(val dataBaseDoa: SleepDataBaseDoa,val application: Application): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)){

            return SleepTrackerViewModel(dataBaseDoa,application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}