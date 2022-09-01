package com.durini.solucionlab7

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout

class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var buttonLogin: Button
    private lateinit var textCreateUser: TextView
    private lateinit var inputLayout: TextInputLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin = view.findViewById(R.id.button_loginFragment_login)
        textCreateUser = view.findViewById(R.id.text_loginFragment_createUser)
        inputLayout = view.findViewById(R.id.inputLayout_loginFragment_email)

        setListeners()
    }

    private fun setListeners() {
        buttonLogin.setOnClickListener {
            val email = inputLayout.editText!!.text.toString()
            if (email == "jcdurini@uvg.edu.gt") {
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                    email = email
                )
                requireView().findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), getString(R.string.user_not_found), Toast.LENGTH_LONG).show()
            }
        }

        textCreateUser.setOnClickListener {
            requireView().findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToNewAccountFragment()
            )
        }
    }
}