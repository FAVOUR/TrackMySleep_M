package com.example.trackmysleep.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class SleepNight(
 @PrimaryKey(autoGenerate = true)
 val nightId:Long=0L,

 @ColumnInfo(name="start_time_mill")
 val startTimeMill:Long = System.currentTimeMillis(),

 @ColumnInfo(name = "end_time_mill")
 val endTimeMill:Long = startTimeMill,

 @ColumnInfo(name = "quality_rating")
 val sleepQuality:Int =-1
)