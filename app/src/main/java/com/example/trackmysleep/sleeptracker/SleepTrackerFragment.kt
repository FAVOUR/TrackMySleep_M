package com.example.trackmysleep.sleeptracker

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.trackmysleep.R
import com.example.trackmysleep.database.SleepDatabase
import com.example.trackmysleep.databinding.SleepTrackerFragmentBinding

class SleepTrackerFragment : Fragment() {



    private lateinit var viewModel: SleepTrackerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding:SleepTrackerFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.sleep_tracker_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = SleepDatabase.getInstance(application).sleepDataBaseDoa

        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

        val sleepTrackerViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(SleepTrackerViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.sleepTrackerViewModel = sleepTrackerViewModel

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}