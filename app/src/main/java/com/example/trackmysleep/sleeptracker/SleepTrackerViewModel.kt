package com.example.trackmysleep.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.trackmysleep.database.SleepDataBaseDoa
import com.example.trackmysleep.database.SleepNight
import com.example.trackmysleep.formatNights
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SleepTrackerViewModel(val datase:SleepDataBaseDoa,val application: Application) : AndroidViewModel() {



private var viewModelJob =Job()

    private  val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val nights = datase.getAllNight()

    val nightString = Transformations.map(nights){ nights ->
        formatNights(nights,application.resources)
    }

    private var tonight = MutableLiveData< SleepNight?>()





}