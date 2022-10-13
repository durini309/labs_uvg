package com.durini.solucionlab12.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.durini.solucionlab12.R
import com.durini.solucionlab12.presentation.SessionViewModel
import com.durini.solucionlab12.databinding.FragmentHomeBinding
import com.durini.solucionlab12.presentation.util.visibleIf
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val sessionViewModel: SessionViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservables()
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            buttonHomeFragmentDefault.setOnClickListener {
                homeViewModel.changeState(HomeViewModel.HomeViewState.Default)
            }

            buttonHomeFragmentSuccess.setOnClickListener {
                homeViewModel.changeState(HomeViewModel.HomeViewState.Success)
            }

            buttonHomeFragmentFailure.setOnClickListener {
                homeViewModel.changeState(HomeViewModel.HomeViewState.Failure)
            }

            buttonHomeFragmentEmpty.setOnClickListener {
                homeViewModel.changeState(HomeViewModel.HomeViewState.Empty)
            }

            buttonHomeFragmentKeepSession.setOnClickListener {
                sessionViewModel.cancelJob()
            }

            buttonHomeFragmentLogOut.setOnClickListener {
                sessionViewModel.logOut()
            }
        }
    }

    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            sessionViewModel.validAuthToken.collectLatest { isValid ->
                if (!isValid) {
                    Toast.makeText(requireContext(), "logout", Toast.LENGTH_SHORT).show()
                    requireActivity().onBackPressed()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            homeViewModel.homeState.collectLatest { state ->
                handleHomeState(state)
            }
        }

    }

    private fun handleHomeState(state: HomeViewModel.HomeViewState) {
        when(state) {
            HomeViewModel.HomeViewState.Default -> {
                setStateLoading(false)
                enableButtons()
                binding.iconHomeFragmentStatus.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_android)
                )
                binding.textHomeFragmentDescription.text = "Selecciona una opción"
            }

            HomeViewModel.HomeViewState.Empty -> {
                setStateLoading(false)
                enableButtons()
                binding.iconHomeFragmentStatus.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_empty)
                )
                binding.textHomeFragmentDescription.text = "Sin resultados"
            }

            HomeViewModel.HomeViewState.Failure -> {
                setStateLoading(false)
                enableButtons()
                binding.iconHomeFragmentStatus.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_failure)
                )
                binding.textHomeFragmentDescription.text = "¡Operación fallida!"
            }

            HomeViewModel.HomeViewState.Success -> {
                setStateLoading(false)
                enableButtons()
                binding.iconHomeFragmentStatus.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_success)
                )
                binding.textHomeFragmentDescription.text = "¡Operación exitosa!"
            }

            is HomeViewModel.HomeViewState.Loading -> {
                setStateLoading(true)
                binding.buttonHomeFragmentDefault.isEnabled = state.currentState == HomeViewModel.HomeViewState.Default
                binding.buttonHomeFragmentSuccess.isEnabled = state.currentState == HomeViewModel.HomeViewState.Success
                binding.buttonHomeFragmentFailure.isEnabled = state.currentState == HomeViewModel.HomeViewState.Failure
                binding.buttonHomeFragmentEmpty.isEnabled = state.currentState == HomeViewModel.HomeViewState.Empty
            }
        }
    }

    private fun setStateLoading(isLoading: Boolean) {
        binding.progressHomeFragment.visibleIf(isLoading)
        binding.iconHomeFragmentStatus.visibleIf(!isLoading)
        binding.textHomeFragmentDescription.visibleIf(!isLoading)
    }

    private fun enableButtons() {
        binding.buttonHomeFragmentDefault.isEnabled = true
        binding.buttonHomeFragmentEmpty.isEnabled = true
        binding.buttonHomeFragmentFailure.isEnabled = true
        binding.buttonHomeFragmentSuccess.isEnabled = true
    }

}