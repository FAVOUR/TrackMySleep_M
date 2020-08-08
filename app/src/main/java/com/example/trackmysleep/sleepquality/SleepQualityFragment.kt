package com.example.trackmysleep.sleepquality

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.trackmysleep.R
import com.example.trackmysleep.database.SleepDatabase
import com.example.trackmysleep.databinding.SleepQualityFragmentBinding

class SleepQualityFragment : Fragment() {

//    companion object {
//        fun newInstance() = SleepQualityFragment()
//    }

    private lateinit var sleepQualityViewModel: SleepQualityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val dataBinding :SleepQualityFragmentBinding =  DataBindingUtil.inflate(inflater,R.layout.sleep_quality_fragment,container,false)

        val argument =SleepQualityFragmentArgs.fromBundle(requireArguments())
        val dataSource = SleepDatabase.getInstance(requireActivity().application).sleepDataBaseDoa

        val sleepQualityViewModelFactory = SleepQualityViewModelFactory(argument.sleepNightKey,dataSource)
         sleepQualityViewModel= ViewModelProviders.of(this,sleepQualityViewModelFactory).get(SleepQualityViewModel::class.java)

        sleepQualityViewModel._navigateToSleepTracker.observe(viewLifecycleOwner, Observer { night ->
             night?.let {

                   findNavController().navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                 sleepQualityViewModel.doneNavigating()

             }


        })


        return  dataBinding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}