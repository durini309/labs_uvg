package com.durini.solucionlab10.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.durini.solucionlab10.R
import com.durini.solucionlab10.ui.*
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var inputEmail: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var buttonLogin: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            inputEmail = findViewById(R.id.inputLayout_loginFragment_email)
            inputPassword = findViewById(R.id.inputLayout_loginFragment_password)
            buttonLogin = findViewById(R.id.button_loginFragment_login)
        }

        checkIsLogged()
        setListeners()
    }

    private fun checkIsLogged() {
        CoroutineScope(Dispatchers.IO).launch {
            val currentUser = requireContext().dataStore.getPreferencesValue(KEY_EMAIL)
            if (currentUser != null) {
                navigateToListScreen()
            }
        }
    }

    private fun setListeners() {
        buttonLogin.setOnClickListener {
            loginUser(
                email = inputEmail.editText!!.text.toString(),
                password = inputPassword.editText!!.text.toString()
            )
        }
    }

    private fun loginUser(email: String, password: String) {
        if ((email == getString(R.string.my_email)) && email == password) {
            saveLoggedUser(email)
        } else {
            Toast.makeText(requireContext(), getString(R.string.error_email_password), Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToListScreen() {
        CoroutineScope(Dispatchers.Main).launch {
            requireView().findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToCharacterListFragment()
            )
        }
    }

    private fun saveLoggedUser(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            requireContext().dataStore.savePreferencesValue(KEY_EMAIL, email)
            navigateToListScreen()
        }
    }
}