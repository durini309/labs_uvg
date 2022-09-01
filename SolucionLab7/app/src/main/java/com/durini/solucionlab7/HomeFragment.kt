package com.durini.solucionlab7

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var buttonProfile: Button
    private lateinit var textContent: TextView

    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonProfile = view.findViewById(R.id.button_homeFragment_profile)
        textContent = view.findViewById(R.id.text_homeFragment_content)

        setListeners()
        setContent()
    }

    private fun setContent() {
        textContent.text = getString(R.string.home_content).format(args.email)
    }

    private fun setListeners() {
        buttonProfile.setOnClickListener {
            requireView().findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProfileFragment(
                    email = args.email
                )
            )
        }
    }
}