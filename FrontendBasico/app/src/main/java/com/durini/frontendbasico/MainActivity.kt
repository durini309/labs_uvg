package com.durini.frontendbasico

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnWidgets: MaterialButton
    private lateinit var btnLinearLayout: MaterialButton
    private lateinit var btnRelativeLayout: MaterialButton
    private lateinit var btnConstraintLayout: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWidgets = findViewById(R.id.btn_mainActivity_widgets)
        btnLinearLayout = findViewById(R.id.btn_mainActivity_linearLayout)
        btnRelativeLayout = findViewById(R.id.btn_mainActivity_relativeLayout)
        btnConstraintLayout = findViewById(R.id.btn_mainActivity_constraintLayout)

        initListeners()
    }

    private fun initListeners() {
        btnWidgets.setOnClickListener {
            startActivity(Intent(this, WidgetsActivity::class.java))
        }

        btnLinearLayout.setOnClickListener {
            startActivity(Intent(this, LinearLayoutActivity::class.java))
        }

        btnRelativeLayout.setOnClickListener {
            startActivity(Intent(this, RelativeLayoutActivity::class.java))
        }

        btnConstraintLayout.setOnClickListener {
            startActivity(Intent(this, ConstraintLayoutActivity::class.java))
        }
    }
}