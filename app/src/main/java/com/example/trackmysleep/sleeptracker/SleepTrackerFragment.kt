package com.example.trackmysleep.sleeptracker

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.trackmysleep.R
import com.example.trackmysleep.database.SleepDatabase
import com.example.trackmysleep.databinding.SleepTrackerFragmentBinding
import com.example.trackmysleep.sleeptracker.adapter.SleepNightAdapter
import com.example.trackmysleep.sleeptracker.adapter.SleepNightListener
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

        val manager = GridLayoutManager(activity, 3)
        binding.sleepList.layoutManager = manager

        manager.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return  when(position){
                    0 ->3
                    else ->1
                }

            }

        }

        sleepTrackerViewModel.navigateToSleepQuality.observe(this, Observer { night ->
            night?.let {
                this.findNavController().navigate(
                    SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(it.nightId) )
                   sleepTrackerViewModel.doneNavigating()

            }
        })

        val adapter = SleepNightAdapter(SleepNightListener { night ->
            sleepTrackerViewModel.onNightSleepClicked(night)
            Toast.makeText(requireContext(),"I have been clicked I am sleep no 2 ",Toast.LENGTH_SHORT).show()
        })


        binding.sleepList.adapter = adapter

       sleepTrackerViewModel.navigateToSleepDetail.observe(this, Observer {night ->
           night?.let {  findNavController().navigate(SleepTrackerFragmentDirections.actionSleepTrackerFragmentToSleepDetailFragment(night))
               sleepTrackerViewModel.onNightSleepDetailNavigated()
           }
       })

        sleepTrackerViewModel.nights.observe(this, Observer{
             it?.let {
                 adapter.addHeaderAndSubmitList(it)

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