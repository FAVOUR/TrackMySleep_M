package com.example.trackmysleep

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.trackmysleep.database.SleepDataBaseDoa
import com.example.trackmysleep.database.SleepDatabase
import com.example.trackmysleep.database.SleepNight
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest  {

    lateinit var taskDoa:SleepDataBaseDoa
    lateinit var sleepDatabase:SleepDatabase
    lateinit var sleepDao:SleepDataBaseDoa

    @Before
    fun create(){
        val context: Context = ApplicationProvider.getApplicationContext()
        sleepDatabase= Room.databaseBuilder(context,SleepDatabase::class.java,"sleepDp")
                            .allowMainThreadQueries()
                            .build()

        sleepDao=sleepDatabase.sleepDataBaseDoa
    }


    @After
    fun destroyDatabase(){
        sleepDatabase.close()
    }


    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val night = SleepNight()
        sleepDao.insert(night)
        val tonight = sleepDao.getToNight()
        Assert.assertEquals(tonight?.sleepQuality, -1)
    }





}