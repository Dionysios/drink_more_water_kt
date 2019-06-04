package com.dionpapas.drink_more_water.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dionpapas.drink_more_water.R
import com.dionpapas.drink_more_water.database.WaterEntry
import com.dionpapas.drink_more_water.viewmodels.MainFragmentViewModel

class MainFragment : androidx.fragment.app.Fragment() {

    private lateinit var mainFragmentViewModel: MainFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mainFragmentViewModel = ViewModelProviders.of(this)
            .get(MainFragmentViewModel::class.java)

        mainFragmentViewModel.getLatestCounter().observe(this, Observer<WaterEntry> {
            Toast.makeText(context, "Water entry not saved!", Toast.LENGTH_SHORT).show()
        })

        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

}
