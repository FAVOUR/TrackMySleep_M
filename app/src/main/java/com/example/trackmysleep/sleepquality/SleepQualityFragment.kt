package com.example.trackmysleep.sleepquality

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.trackmysleep.R
import com.example.trackmysleep.databinding.SleepQualityFragmentBinding

class SleepQualityFragment : Fragment() {

//    companion object {
//        fun newInstance() = SleepQualityFragment()
//    }

    private lateinit var viewModel: SleepQualityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val dataBinding :SleepQualityFragmentBinding =  DataBindingUtil.inflate(inflater,R.layout.sleep_quality_fragment,container,false)

        return  dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SleepQualityViewModel::class.java)
    }

}