package com.durini.frontendavanzado.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durini.frontendavanzado.R
import com.durini.frontendavanzado.adapters.PlaceAdapter
import com.durini.frontendavanzado.database.Database
import com.durini.frontendavanzado.entities.Place

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
    }
}