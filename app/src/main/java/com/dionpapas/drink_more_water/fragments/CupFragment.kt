package com.dionpapas.drink_more_water.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dionpapas.drink_more_water.adapters.CupListAdapter
import com.dionpapas.drink_more_water.data.CupImageAssets
import android.widget.AdapterView
import com.dionpapas.drink_more_water.R
import android.content.Context
import android.widget.GridView

class CupFragment : androidx.fragment.app.Fragment()  {

    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    var mCallback: OnImageCupClickListener? = null

    interface OnImageCupClickListener {
        fun onImageCupSelected(position: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = context as OnImageCupClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnImageClickListener")
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_cup, container, false)
        val gridView = view.findViewById(R.id.images_grid_view) as GridView
        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the image resources to display
        val mAdapter = CupListAdapter(requireContext(), CupImageAssets.cups)
        gridView.adapter = mAdapter
        gridView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, l ->
            // Trigger the callback method and pass in the position that was clicked
            mCallback?.onImageCupSelected(position)
        }

        // Return the root view
        return view
    }
}
