package com.example.trackmysleep.sleeptracker

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.trackmysleep.R
import com.example.trackmysleep.database.SleepDatabase
import com.example.trackmysleep.databinding.SleepTrackerFragmentBinding
import com.example.trackmysleep.sleeptracker.adapter.SleepNightAdapter
import com.google.android.material.snackbar.Snackbar

class SleepTrackerFragment : Fragment() {




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

        binding.lifecycleOwner = this

        binding.sleepTrackerViewModel = sleepTrackerViewModel


        sleepTrackerViewModel.navigateToSleepQuality.observe(this, Observer { night ->
            night?.let {
                this.findNavController().navigate(
                    SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(it.nightId)
                )
                sleepTrackerViewModel.doneNavigating()

            }
        })

        val adapter = SleepNightAdapter()


        binding.listItem.adapter = adapter



        sleepTrackerViewModel.nights.observe(this, Observer{
             it?.let {
                 adapter.submitList(it)

             }
        })

        sleepTrackerViewModel.showSnackBarEvent.observe(this, Observer {
           if(it == true) {
                Snackbar.make(requireActivity().findViewById(android.R.id.content),getString(R.string.cleared_message),Snackbar.LENGTH_SHORT).show()
               sleepTrackerViewModel.doneDisplayingSnackBar()
           }
        })

        return binding.root

    }




}