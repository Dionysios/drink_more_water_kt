package com.dionpapas.drink_more_water.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dionpapas.drink_more_water.database.WaterEntry
import com.dionpapas.drink_more_water.database.WaterEntryRepository

class DairyFragmentViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: WaterEntryRepository = WaterEntryRepository(application)
    private var allWaterEntries: LiveData<List<WaterEntry>> = repository.getAllWaterEntries()

    fun getAllWaterEntries(): LiveData<List<WaterEntry>> {
        return allWaterEntries
    }
}