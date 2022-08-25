package com.durini.jetpacknavigation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController


class CreateUserFragment : Fragment(R.layout.fragment_create_user) {

    private lateinit var buttonCreate: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonCreate = view.findViewById(R.id.button_createFragment_home)
        setListeners()
    }

    private fun setListeners() {
        buttonCreate.setOnClickListener {
            val action = CreateUserFragmentDirections.actionCreateUserFragmentToHomeFragment(
                email = "cserrano@gmail.com",
                fullName = "Carlos Serrano"
            )
            requireView().findNavController().navigate(action)
        }
    }
}