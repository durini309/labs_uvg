package com.durini.viewmodel.observables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.durini.viewmodel.databinding.ActivityObservableBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ObservableActivity : AppCompatActivity() {

    private val viewModel: ObservableViewModel by viewModels()
    private lateinit var binding: ActivityObservableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObservableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservables()
        setListeners()
    }

    private fun setListeners() {
        binding.buttonLiveData.setOnClickListener {
            viewModel.triggerLiveData()
        }

        binding.buttonFlow.setOnClickListener {
            lifecycleScope.launch {
                viewModel.triggerFlow().collectLatest { newText ->
                    binding.txtFlow.text = newText
                }
            }
        }

        binding.buttonStateFlow.setOnClickListener {
            viewModel.triggerStateFlow()
        }
    }

    private fun setObservables() {
        viewModel.liveData.observe(this) { newText ->
            binding.txtLiveData.text = newText
        }

        lifecycleScope.launchWhenStarted {
            viewModel.stateFlow.collectLatest { newText ->
                binding.txtStateFlow.text = newText
            }
        }
    }
}