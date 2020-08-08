package com.example.trackmysleep.sleepquality

import android.icu.text.LocaleDisplayNames
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trackmysleep.database.SleepDataBaseDoa
import com.example.trackmysleep.database.SleepNight
import kotlinx.coroutines.*

class SleepQualityViewModel(val sleepQuality:Long=0L,val sleepDataBaseDoa: SleepDataBaseDoa) : ViewModel() {

    var job = Job()
       var uiScope= CoroutineScope(Dispatchers.Main + job)

      val navigateToSleepTracker :LiveData<Boolean>
       get() =_navigateToSleepTracker


      val _navigateToSleepTracker = MutableLiveData<Boolean>()

          fun doneNavigating(){
              _navigateToSleepTracker.value = null
          }


      fun selectViewQuality(sleep:Int){
          Log.d("TAG", "selectViewQuality: Triggered with $sleep")
          //Main safe  call can be made
          uiScope.launch {

              //Serves as a suspending function
                  withContext(Dispatchers.IO) {
                      var sleepNight =sleepDataBaseDoa.get(sleepQuality) ?: return@withContext

                      sleepNight.sleepQuality = sleep
                      sleepDataBaseDoa.update(sleepNight)
                  }

              _navigateToSleepTracker.value=true
          }
      }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}