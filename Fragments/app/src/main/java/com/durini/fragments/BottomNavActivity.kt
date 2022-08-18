package com.durini.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)

        bottomNav = findViewById(R.id.bottomNav_bottomNavActivity)
        setCurrentFragment(HomeFragment())
        setListeners()
    }

    private fun setListeners() {
        bottomNav.setOnItemSelectedListener {
            // Dependiendo el item del menu, mostramos el fragment deseado
            when(it.itemId) {
                R.id.item_home -> setCurrentFragment(HomeFragment())
                R.id.item_settings -> setCurrentFragment(SettingsFragment())
            }
            true
        }
    }

    /**
     * Función que reemplaza el fragment actual. No usamos backstack porque
     * para este tipo de navegación, no es el comportamiento esperado.
     */
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragmentContainer_bottomNavActivity, fragment)
        }
    }
}