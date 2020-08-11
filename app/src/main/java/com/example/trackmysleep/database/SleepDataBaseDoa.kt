package com.example.trackmysleep.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SleepDataBaseDoa {
 @Insert
 fun insert (night:SleepNight)

 @Update
 fun update(night:SleepNight)

 @Query("SELECT * FROM daily_sleep_quality_table WHERE nightId = :key  ")
  fun get(key:Long):SleepNight?

 @Query("DELETE  FROM daily_sleep_quality_table")
  fun clear ()

 @Query ("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1 ")
  fun getToNight():SleepNight?

 @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC ")
  fun getAllNight () : LiveData<List<SleepNight>>


 /**
  * Selects and returns the night with given nightId.
  */
 @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :key")
 fun getNightWithId(key: Long): LiveData<SleepNight>

}