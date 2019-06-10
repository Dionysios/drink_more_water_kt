package com.dionpapas.drink_more_water.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dionpapas.drink_more_water.R
import com.dionpapas.drink_more_water.database.WaterEntry
import com.dionpapas.drink_more_water.viewmodels.MainFragmentViewModel

class MainFragment : androidx.fragment.app.Fragment() {

    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    private lateinit var statusTextView: TextView
    private lateinit var mImageView: ImageView
    private var waterCounter: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)


//        statusTextView.setOnClickListener {
//            Log.d("Incoming", "Selected1")
//        }
        // Return the fragment view/layout
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        // Get the custom view for this fragment layout
        // Get the text view widget reference from custom layout
        val tv = view.findViewById<TextView>(R.id.tv_water_count)
        // Set a click listener for text view object
        mainFragmentViewModel = ViewModelProviders.of(requireActivity()).get(MainFragmentViewModel::class.java)

        //  statusTextView = view.findViewById(R.id.tv_water_count)
        mainFragmentViewModel.getLatestCounter().observe(requireActivity(), Observer<WaterEntry> {
            //Toast.makeText(context, "Water entry not saved!", Toast.LENGTH_SHORT).show()
            //waterCounter = it.counter
        })

        tv.text = waterCounter.toString()
        mImageView = view.findViewById<ImageView>(R.id.ib_water_increment)

        mImageView.setOnClickListener{
            // Change the text color
            //tv.text = "2"
            // Show click confirmation
            Toast.makeText(view.context,"TextView clicked.",Toast.LENGTH_SHORT).show()
            waterCounter++
            tv.text = waterCounter.toString()
        }
        // Return the fragment view/layout
    }
}

