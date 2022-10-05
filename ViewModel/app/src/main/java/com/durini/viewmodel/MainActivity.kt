package com.durini.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.durini.viewmodel.databinding.ActivityMainBinding
import com.durini.viewmodel.observables.ObservableActivity
import com.durini.viewmodel.scope.ScopeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.buttonMainActivityObservables.setOnClickListener {
            Intent(this, ObservableActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.buttonMainActivityScope.setOnClickListener {
            Intent(this, ScopeActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}