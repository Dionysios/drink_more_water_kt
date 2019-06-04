package com.dionpapas.drink_more_water.database;

import androidx.lifecycle.LiveData;
import androidx.room.*

@Dao
interface WaterEntryDAO {

    @Query("SELECT * FROM DailyWaterEntries ORDER BY updated_at")
    fun getAllWaterEntries(): LiveData<List<WaterEntry>>

    @Insert
    fun insertWaterEntry(waterEntry:WaterEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWaterEntry(waterEntry:WaterEntry)

    @Delete
    fun deleteWaterEntry(waterEntry: WaterEntry)

    @Query("SELECT id,counter,updated_at FROM DailyWaterEntries ORDER BY updated_at")
    fun getTodayValue(): LiveData<WaterEntry>

}