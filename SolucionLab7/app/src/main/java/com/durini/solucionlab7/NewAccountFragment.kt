package com.durini.solucionlab7

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout

class NewAccountFragment: Fragment(R.layout.fragment_new_account) {

    private lateinit var buttonCreate: Button
    private lateinit var inputLayoutEmail: TextInputLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonCreate = view.findViewById(R.id.button_newAccountFragment_login)
        inputLayoutEmail = view.findViewById(R.id.inputLayout_newAccountFragment_email)
        setListeners()
    }

    private fun setListeners() {
        buttonCreate.setOnClickListener {
            requireView().findNavController().navigate(
                NewAccountFragmentDirections.actionNewAccountFragmentToHomeFragment(
                    email = inputLayoutEmail.editText!!.text.toString()
                )
            )
        }
    }
}