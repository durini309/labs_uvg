package com.durini.roomdemo.presentation.user

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.durini.roomdemo.R
import com.durini.roomdemo.data.local_source.Database
import com.durini.roomdemo.domain.model.User
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserFragment : Fragment(R.layout.fragment_user) {


    private lateinit var inputName: TextInputLayout
    private lateinit var inputCountry: TextInputLayout
    private lateinit var inputDob: TextInputLayout
    private lateinit var buttonSave: Button
    private lateinit var database: Database
    private lateinit var currentUser: User
    private val args: UserFragmentArgs by navArgs()
    private var createUser: Boolean = false



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            inputName = findViewById(R.id.inputLayout_createUser_name)
            inputCountry = findViewById(R.id.inputLayout_createUser_country)
            inputDob = findViewById(R.id.inputLayout_createUser_dob)
            buttonSave = findViewById(R.id.button_saveUser)
        }

        database = Room.databaseBuilder(
            requireContext(),
            Database::class.java,
            "dbname"
        ).build()

        if (args.id == -1) {
            createUser = true
        } else {
            fetchUser()
        }

        initData()
        setListeners()
    }

    private fun initData() {
        buttonSave.text = if (createUser)
            "Crear"
        else
            "Actualizar"
    }

    private fun setListeners() {
        buttonSave.setOnClickListener {
            if (createUser)
                createUser()
            else
                updateUser()
        }
    }

    private fun fetchUser() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = database.userDao().getUserById(args.id)
            CoroutineScope(Dispatchers.Main).launch {
                if (user != null) {
                    inputName.editText!!.setText(user.fullname)
                    inputCountry.editText!!.setText(user.country)
                    inputDob.editText!!.setText(user.age.toString())
                    currentUser = user
                } else {
                    Toast.makeText(
                        requireContext(),
                        "No se encontro usuario con ID ${args.id}",
                        Toast.LENGTH_LONG
                    ).show()
                    requireActivity().onBackPressed()
                }
            }
        }
    }

    private fun createUser() {
        val user = User(
            fullname = inputName.editText!!.text.toString(),
            country = inputCountry.editText!!.text.toString(),
            age = inputDob.editText!!.text.toString().toInt()
        )
        CoroutineScope(Dispatchers.IO).launch {
            database.userDao().insert(user)
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    requireContext(),
                    "Usuario creado exitosamente",
                    Toast.LENGTH_LONG
                ).show()
                requireActivity().onBackPressed()
            }
        }
    }

    private fun updateUser() {
        val updatedUser = currentUser.copy(
            fullname = inputName.editText!!.text.toString(),
            country = inputCountry.editText!!.text.toString(),
            age = inputDob.editText!!.text.toString().toInt()
        )

        CoroutineScope(Dispatchers.IO).launch {
            database.userDao().update(updatedUser)
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    requireContext(),
                    "Usuario actualizado exitosamente",
                    Toast.LENGTH_LONG
                ).show()
                requireActivity().onBackPressed()
            }
        }
    }
}