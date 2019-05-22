package com.dionpapas.drink_more_water

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.dionpapas.drink_more_water.fragments.CupFragment
import com.dionpapas.drink_more_water.fragments.MainFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_drawer.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,CupFragment.OnImageCupClickListener {
    private lateinit var fragmentManager: androidx.fragment.app.FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init()
    }

    private fun init(){
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
                fragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()
            }
            R.id.nav_settings -> {
                fragmentManager.beginTransaction().replace(R.id.fragment_container, MainFragment()).commit()
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
}
