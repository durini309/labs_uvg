package com.durini.frontendavanzado.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.durini.frontendavanzado.R

class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var buttonToDetails: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonToDetails = view.findViewById(R.id.button_homeFragment_details)
        setListeners()
    }

    private fun setListeners() {
        buttonToDetails.setOnClickListener {
            requireView().findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
            )
        }
    }

}