package com.durini.frontendavanzado.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.durini.frontendavanzado.R

class PlaceDetailsFragment : Fragment(R.layout.fragment_place_details) {

    private val args: PlaceDetailsFragmentArgs by navArgs()
    private lateinit var textDetails: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textDetails = view.findViewById(R.id.text_placeDetails)
        textDetails.text = args.place.toString()
    }
}