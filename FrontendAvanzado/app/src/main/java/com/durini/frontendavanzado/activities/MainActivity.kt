package com.durini.frontendavanzado.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.durini.frontendavanzado.R

class MainActivity : AppCompatActivity() {

    private lateinit var buttonAppbar: Button
    private lateinit var buttonImageLoader: Button
    private lateinit var buttonRecyclerView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAppbar = findViewById(R.id.button_mainActivity_appbar)
        buttonImageLoader = findViewById(R.id.button_mainActivity_imageLoader)
        buttonRecyclerView = findViewById(R.id.button_mainActivity_recyclerView)

        setListeners()
    }

    private fun setListeners() {
        buttonAppbar.setOnClickListener {
            startActivity(Intent(this, TopAppBarActivity::class.java))
        }

        buttonImageLoader.setOnClickListener {
            startActivity(Intent(this, ImageLoaderActivity::class.java))
        }

        buttonRecyclerView.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }
    }
}