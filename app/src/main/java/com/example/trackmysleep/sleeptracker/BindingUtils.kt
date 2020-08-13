package com.example.trackmysleep.sleeptracker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.trackmysleep.R
import com.example.trackmysleep.convertDurationToFormatted
import com.example.trackmysleep.convertNumericQualityToString
import com.example.trackmysleep.database.SleepNight



    @BindingAdapter("sleepDurationFormatted")
    fun TextView.setSleepDurationFormatted(item: SleepNight) {
        text = convertDurationToFormatted(
            item.startTimeMill,
            item.endTimeMill,
            context.resources
        )

    }


    @BindingAdapter("sleepQualityString")
    fun TextView.setSleepQualityString(item: SleepNight) {
        text = convertNumericQualityToString(
            item.sleepQuality,
            context.resources
        )
    }



    @BindingAdapter("sleepImage")
    fun ImageView.setImage(item:SleepNight) {
        setImageResource(
            when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            }
        )
    }
