package com.durini.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var buttonCount: Button
    private var count = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonCount = view.findViewById(R.id.button_secondFragment_count)
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        buttonCount.text = count.toString()
    }

    private fun setListeners() {
        buttonCount.setOnClickListener {
            count++
            buttonCount.text = count.toString()
        }
    }
}