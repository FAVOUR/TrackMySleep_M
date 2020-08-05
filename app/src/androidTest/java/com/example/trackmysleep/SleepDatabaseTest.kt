package com.example.trackmysleep

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.trackmysleep.database.SleepDataBaseDoa
import com.example.trackmysleep.database.SleepDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest  {

    lateinit var taskDoa:SleepDataBaseDoa
    lateinit var sleepDatabase:SleepDatabase

    @Before
    fun create(){
        val context: Context = ApplicationProvider.getApplicationContext()
        sleepDatabase= Room.databaseBuilder(context,SleepDatabase::class.java,"sleepDp")
                            .allowMainThreadQueries()
                            .build()
    }


    @After
    fun destroyDatabase(){
        sleepDatabase.close()
    }





}