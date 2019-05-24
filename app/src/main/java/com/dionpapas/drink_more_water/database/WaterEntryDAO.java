package com.dionpapas.drink_more_water.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WaterEntryDAO {
    @Query("SELECT * FROM DailyWaterEntries ORDER BY updated_at")
    LiveData<List<WaterEntry>> getAllWaterEntries();

    @Insert
    void insertWaterEntry(WaterEntry waterEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateWaterEntry(WaterEntry waterEntry);

    @Delete
    void deleteWaterEntry(WaterEntry waterEntry);
}
