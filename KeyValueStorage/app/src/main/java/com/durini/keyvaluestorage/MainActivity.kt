package com.durini.keyvaluestorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var buttonShared: Button
    private lateinit var buttonDataStore: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonShared = findViewById(R.id.button_sharedPreferences)
        buttonDataStore = findViewById(R.id.button_dataStore)
        setListeners()
    }

    private fun setListeners() {
        buttonShared.setOnClickListener {
            startActivity(Intent(this, SharedPreferencesActivity::class.java))
        }

        buttonDataStore.setOnClickListener {
            startActivity(Intent(this, DataStoreActivity::class.java))
        }
    }
}