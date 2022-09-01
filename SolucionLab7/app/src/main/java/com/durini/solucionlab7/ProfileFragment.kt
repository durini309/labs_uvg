package com.durini.solucionlab7

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputLayout

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var buttonUpdate: Button
    private lateinit var inputLayoutEmail: TextInputLayout
    private val args: ProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonUpdate = view.findViewById(R.id.button_profileFragment_update)
        inputLayoutEmail = view.findViewById(R.id.inputLayout_profileFragment_email)

        setListeners()
        setContent()
    }

    private fun setContent() {
        inputLayoutEmail.editText!!.setText(args.email)
    }

    private fun setListeners() {
        buttonUpdate.setOnClickListener {
            requireView().findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            )
        }
    }
}