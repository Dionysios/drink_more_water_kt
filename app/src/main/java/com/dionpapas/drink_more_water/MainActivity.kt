package com.dionpapas.drink_more_water

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.dionpapas.drink_more_water.fragments.CupFragment
import com.dionpapas.drink_more_water.fragments.DairyFragment
import com.dionpapas.drink_more_water.fragments.MainFragment
import com.dionpapas.drink_more_water.fragments.SettingsFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_drawer.*
import android.content.SharedPreferences
import android.util.Log
import com.dionpapas.drink_more_water.utils.Utils.KEY_WATER_COUNT
import androidx.preference.PreferenceManager


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,CupFragment.OnImageCupClickListener,
    SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var fragmentManager: androidx.fragment.app.FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initUI()
        setupSharedPreferences()
    }

    private fun initUI(){
        fragmentManager = supportFragmentManager
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        fragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_main -> {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()
            }
            R.id.nav_dairy -> {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, DairyFragment()).commit()
            }
            R.id.nav_settings -> {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, SettingsFragment()).commit()
            }
            R.id.nav_cup -> {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, CupFragment()).commit()
            }
        }
        val drawerLayout: androidx.drawerlayout.widget.DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onImageCupSelected(position: Int) {
        Toast.makeText(this,"Position clicked = $position", Toast.LENGTH_LONG).show()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        Log.i("TAG", "onStartJob something changed $key")
        when {
            KEY_WATER_COUNT == key -> {
                //update water count
                //updateWaterCount()
            }
            getString(R.string.is_alarm_enabled) == key ->
                //ignore
                Log.i("TAG", "this is the alarm activated$key")
            else -> {
                Log.i("TAG", "onStartJob1 something changed $key")
                //default
                //FireBaseJob.cancelAllReminders(this)
                // initializeFireBaseJob(sharedPreferences)
            }
        }
    }

    private fun setupSharedPreferences() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        initializeFireBaseJob(sharedPreferences)
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        Log.i("TAG", "lets see what we get" + sharedPreferences.getBoolean(getString(R.string.is_alarm_enabled), resources.getBoolean(R.bool.pref_when_charg)))
        //todo rethink the check here. is_alarm_enabled maybe not needed
        if (!sharedPreferences.getBoolean(getString(R.string.is_alarm_enabled), resources.getBoolean(R.bool.pref_when_charg))) {
            Log.i("TAG", "lets see what we get 2")
            //should start the alarm of 00:00
          //  triggerAlarmManager()
          //  Utilities.setAlarmActive(this)
        }
    }

    private fun initializeFireBaseJob(sharedPreferences: SharedPreferences) {
        Log.i("TAG", "Calling initiaze")
//        FireBaseJob.initiaze(this,
//            sharedPreferences.getBoolean(getString(R.string.enable_notif_key), resources.getBoolean(R.bool.pref_enable_notif)),
//            sharedPreferences.getBoolean(getString(R.string.notif_on_wifi_key), resources.getBoolean(R.bool.pref_on_wifi)),
//            sharedPreferences.getBoolean(getString(R.string.notif_when_charging_key), resources.getBoolean(R.bool.pref_when_charg)),
//            sharedPreferences.getString(getString(R.string.interval_key), getString(R.string.interval_value))
//        )
    }
}
