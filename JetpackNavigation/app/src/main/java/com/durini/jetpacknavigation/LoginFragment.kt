package com.durini.jetpacknavigation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout

class LoginFragment: Fragment(R.layout.login_fragment) {

    private lateinit var buttonLogin: Button
    private lateinit var inputLayoutEmail: TextInputLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin = view.findViewById(R.id.button_loginFragment_login)
        inputLayoutEmail = view.findViewById(R.id.text_loginFragment_email)

        setListeners()
    }

    private fun setListeners() {
        buttonLogin.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

}