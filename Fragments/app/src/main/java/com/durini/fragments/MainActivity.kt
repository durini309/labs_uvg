package com.durini.fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    private lateinit var buttonFirst: Button
    private lateinit var buttonSecond: Button
    private lateinit var buttonBottom: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonFirst = findViewById(R.id.button_mainActivity_first)
        buttonSecond = findViewById(R.id.button_mainActivity_second)
        buttonBottom = findViewById(R.id.button_mainActivity_bottom)

        /**
         * Hacemos este if porque el activity, de manera automatica, guarda en su
         * savedInstanceState el fragment que tenía actualmente, por lo que el
         * fragment es restaurado automaticamente
         */
        if (savedInstanceState == null) {
            setCurrentFragment(SecondFragment())
        }

        setListeners()
    }

    private fun setListeners() {
        buttonFirst.setOnClickListener {
            setCurrentFragment(
                // Usamos Factory pattern para crear una nueva instancia
                FirstFragment.newInstance("Juan", "Carlos")
            )
        }

        buttonSecond.setOnClickListener {
            setCurrentFragment(SecondFragment())
        }

        buttonBottom.setOnClickListener {
            startActivity(Intent(this, BottomNavActivity::class.java))
        }
    }

    /**
     * Función que reemplaza el fragment actual. Si backstack porque
     * ese es el comportamiento que queremos para la vista.
     */
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null) // Agregamos al backstack
            replace(R.id.fragmentContainer_mainActivity, fragment)
        }
    }
}