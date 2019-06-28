package com.dionpapas.drink_more_water.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dionpapas.drink_more_water.R
import com.dionpapas.drink_more_water.database.WaterEntry
import java.text.SimpleDateFormat
import java.util.*


class DairyAdapter : ListAdapter<WaterEntry, DairyAdapter.WaterEntryHolder>(DIFF_CALLBACK) {
    // Constant for date format
    private val DATE_FORMAT = "dd/MM/yyy"
    private var mwaterEntries: List<WaterEntry>? = null
    // Date formatter
    private val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WaterEntry>() {
            override fun areContentsTheSame(oldItem: WaterEntry, newItem: WaterEntry): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun areItemsTheSame(oldItem: WaterEntry, newItem: WaterEntry): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterEntryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.water_entries_layout, parent, false)
        return WaterEntryHolder(view)
    }

    override fun onBindViewHolder(holder: WaterEntryHolder, position: Int) {
        val currentWaterEntry: WaterEntry = getItem(position)

        val counter = currentWaterEntry.counter
        val updatedAt = dateFormat.format(currentWaterEntry.updatedAt)

        holder.counterView.text = counter.toString()
        holder.updatedAtView.text = updatedAt

    }

    // Inner class for creating ViewHolders
    inner class WaterEntryHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        // Class variables for the task description and priority TextViews
        //TextView taskDescriptionView;
        var updatedAtView: TextView = itemView.findViewById(R.id.taskUpdatedAt)
        var counterView: TextView = itemView.findViewById(R.id.counterTextView)

        init {
            //taskDescriptionView = itemView.findViewById(R.id.taskDescription);
            itemView.setOnClickListener(this)
        }

    }
}