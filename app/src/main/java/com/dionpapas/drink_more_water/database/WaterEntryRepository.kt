package com.dionpapas.drink_more_water.database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class WaterEntryRepository(application: Application) {

    private var waterEntryDao : WaterEntryDAO

    private var allwaterEntries: LiveData<List<WaterEntry>>

    private var getLatestCounter: LiveData<WaterEntry>

    init {
        val database: AppDatabase = AppDatabase.getInstance(
            application.applicationContext
        )!!
        waterEntryDao = database.taskDao()
        allwaterEntries = waterEntryDao.getAllWaterEntries()
        getLatestCounter = waterEntryDao.getTodayValue()
    }

    fun insert(waterEntry: WaterEntry) {
        val insertwaterEntryAsyncTask = InsertWaterEntryAsyncTask(waterEntryDao).execute(waterEntry)
    }

    fun update(waterEntry: WaterEntry) {
        val updatewaterEntryAsyncTask = UpdateWaterEntryAsyncTask(waterEntryDao).execute(waterEntry)
    }

    fun getLatestCounter(): LiveData<WaterEntry> {
        return getLatestCounter
    }

    companion object {

        private class InsertWaterEntryAsyncTask(waterEntryDao : WaterEntryDAO) : AsyncTask<WaterEntry, Unit, Unit>() {
            val waterEntryDao = waterEntryDao

            override fun doInBackground(vararg p0: WaterEntry?) {
                waterEntryDao.insertWaterEntry(p0[0]!!)
            }
        }

        private class UpdateWaterEntryAsyncTask(waterEntryDao: WaterEntryDAO) : AsyncTask<WaterEntry, Unit, Unit>() {
            val waterEntryDao = waterEntryDao

            override fun doInBackground(vararg p0: WaterEntry?) {
                waterEntryDao.updateWaterEntry(p0[0]!!)
            }
        }
    }
}
