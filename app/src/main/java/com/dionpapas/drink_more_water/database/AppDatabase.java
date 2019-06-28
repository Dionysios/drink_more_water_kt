package com.dionpapas.drink_more_water.database;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import android.util.Log;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;

@Database(entities = {WaterEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "waterReminder";
    private static AppDatabase sInstance;

    public abstract WaterEntryDAO taskDao();

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        // todo change to fallbacktoDestructiveMigration
                        // We will allow this ONLY TEMPORALLY to see that our DB is working
                        .addCallback(roomCallback)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(sInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private WaterEntryDAO waterEntryDAO;

        private PopulateDbAsyncTask(AppDatabase db){
            waterEntryDAO = db.taskDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            waterEntryDAO.insertWaterEntry(new WaterEntry(5, new Date("2019/06/28")));
            return null;
        }
    }
}
