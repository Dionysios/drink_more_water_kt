package com.dionpapas.drink_more_water.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dionpapas.drink_more_water.database.WaterEntry
import com.dionpapas.drink_more_water.database.WaterEntryRepository

class MainFragmentViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: WaterEntryRepository = WaterEntryRepository(application)
    private var getLatestCounter: LiveData<WaterEntry> = repository.getLatestCounter()

    fun insert(waterEntry: WaterEntry) {
        repository.insert(waterEntry)
    }

    fun getLatestCounter(): LiveData<WaterEntry> {
        return getLatestCounter
    }

    fun update(waterEntry: WaterEntry){
        repository.update(waterEntry)
    }
}