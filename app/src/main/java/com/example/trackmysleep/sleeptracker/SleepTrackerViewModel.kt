package com.example.trackmysleep.sleeptracker

import android.app.Application
import androidx.lifecycle.*
import com.example.trackmysleep.database.SleepDataBaseDoa
import com.example.trackmysleep.database.SleepNight
import com.example.trackmysleep.formatNights
import kotlinx.coroutines.*

class SleepTrackerViewModel(
    private val database:SleepDataBaseDoa,
    application: Application) : AndroidViewModel(application) {

     private var _navigateToSleepQuality =MutableLiveData<SleepNight>()

    val navigateToSleepQuality : LiveData<SleepNight>
      get() = _navigateToSleepQuality

private var viewModelJob =Job()

    private  val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val nights = database.getAllNight()

    val nightString = Transformations.map(nights){ nights ->
        formatNights(nights,application.resources)

    }

    private var tonight = MutableLiveData< SleepNight?>()

init {
    initializeTonight()
   }


    private fun initializeTonight() {
        viewModelScope.launch {
            tonight.value =getTonightFromDataBase()
        }
    }

    fun doneNavigating (){
        _navigateToSleepQuality.value =null
    }

    private suspend fun getTonightFromDataBase(): SleepNight? {
       return withContext(Dispatchers.IO){
           var night =database.getToNight()

           if(night?.endTimeMill != night?.startTimeMill){
               night=null
           }
           night
       }
    }


    fun onStartTracking(){
        uiScope.launch {
            val newNight=SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDataBase()
        }
    }

    private suspend fun insert(newNight: SleepNight) {
          withContext(Dispatchers.IO){
              database.insert(night = newNight)
          }
    }

    fun onStopTracking(){
        uiScope.launch {
            val oldNight =tonight.value ?:return@launch
            oldNight.endTimeMill = System.currentTimeMillis()
            update(oldNight)
            _navigateToSleepQuality.value=oldNight

        }
    }

    private suspend fun  update(sleepNight:SleepNight){
        withContext(Dispatchers.IO){
            database.update(sleepNight)
        }
    }


    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }




}