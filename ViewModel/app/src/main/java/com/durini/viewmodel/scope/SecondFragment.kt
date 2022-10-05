package com.durini.viewmodel.scope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.durini.viewmodel.databinding.FragmentSecondBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val scopeViewModel: ScopeViewModel by activityViewModels()
    private val secondViewModel: SecondViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        setObservers()
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            secondViewModel.checkInStatus.collectLatest {
                handleCheckInStatus(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            scopeViewModel.username.collectLatest { username ->
                binding.textSecondFragmentWelcome.text = "Hello, $username"
            }
        }
    }

    private fun setListeners() {
        binding.buttonSecondFragmentCheckIn.setOnClickListener {
            secondViewModel.checkIn()
        }
    }

    private fun handleCheckInStatus(status: SecondViewModel.CheckInStatus) {
        when(status) {
            is SecondViewModel.CheckInStatus.Missing -> {
                binding.apply {
                    buttonSecondFragmentCheckIn.visibility = View.VISIBLE
                    textSecondFragmentErrorMessage.visibility = View.GONE
                    textSecondFragmentSuccessStatus.visibility = View.GONE
                    progressSecondFragment.visibility = View.GONE
                    buttonSecondFragmentCheckIn.text = "Check in"
                }
            }
            is SecondViewModel.CheckInStatus.Failure -> {
                binding.apply {
                    buttonSecondFragmentCheckIn.visibility = View.VISIBLE
                    textSecondFragmentErrorMessage.visibility = View.VISIBLE
                    textSecondFragmentSuccessStatus.visibility = View.GONE
                    progressSecondFragment.visibility = View.GONE
                    buttonSecondFragmentCheckIn.text = "Reintentar"
                    textSecondFragmentErrorMessage.text = status.errorMessage
                }

            }
            is SecondViewModel.CheckInStatus.Loading -> {
                binding.apply {
                    buttonSecondFragmentCheckIn.visibility = View.GONE
                    textSecondFragmentErrorMessage.visibility = View.GONE
                    textSecondFragmentSuccessStatus.visibility = View.GONE
                    progressSecondFragment.visibility = View.VISIBLE
                }
            }
            is SecondViewModel.CheckInStatus.Success -> {
                binding.apply {
                    buttonSecondFragmentCheckIn.visibility = View.GONE
                    textSecondFragmentErrorMessage.visibility = View.GONE
                    textSecondFragmentSuccessStatus.visibility = View.VISIBLE
                    progressSecondFragment.visibility = View.GONE
                }
            }
        }
    }

}