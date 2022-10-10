package com.durini.solucionlab10.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.durini.solucionlab10.R
import com.durini.solucionlab10.data.local.LabDatabase
import com.durini.solucionlab10.data.local.model.Character
import com.durini.solucionlab10.data.remote.api.RetrofitInstance
import com.durini.solucionlab10.data.remote.dto.CharacterDto
import com.durini.solucionlab10.data.remote.dto.mapToModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private val args: CharacterDetailsFragmentArgs by navArgs()
    private lateinit var inputName: TextInputLayout
    private lateinit var inputSpecies: TextInputLayout
    private lateinit var inputGender: TextInputLayout
    private lateinit var inputStatus: TextInputLayout
    private lateinit var inputOrigin: TextInputLayout
    private lateinit var inputEpisodes: TextInputLayout
    private lateinit var imageCharacter: ImageView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var buttonUpdate: Button
    private lateinit var database: LabDatabase
    private lateinit var character: Character


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            inputName = findViewById(R.id.inputLayout_characterDetails_name)
            inputSpecies = findViewById(R.id.inputLayout_characterDetails_species)
            inputGender = findViewById(R.id.inputLayout_characterDetails_gender)
            inputStatus = findViewById(R.id.inputLayout_characterDetails_status)
            inputOrigin = findViewById(R.id.inputLayout_characterDetails_origin)
            inputEpisodes = findViewById(R.id.inputLayout_characterDetails_episodes)
            imageCharacter = findViewById(R.id.image_characterDetails)
            toolbar = findViewById(R.id.toolbar_characterDetails)
            buttonUpdate = findViewById(R.id.button_characterDetails_save)
        }

        database = Room.databaseBuilder(
            requireContext(),
            LabDatabase::class.java,
            "labDatabase"
        ).build()

        setToolbar()
        setListeners()
        getCharacter()
    }

    private fun setToolbar() {
        val navController = findNavController()
        val appbarConfig = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appbarConfig)
    }

    private fun setListeners() {
        toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_details_delete -> {
                    deleteCharacter()
                    true
                }

                R.id.menu_details_sync -> {
                    fetchCharacter()
                    true
                }

                else -> false
            }
        }

        buttonUpdate.setOnClickListener {
            updateCharacter(character.copy(
                name = inputName.editText!!.text.toString(),
                species = inputSpecies.editText!!.text.toString(),
                status = inputStatus.editText!!.text.toString(),
                gender = inputGender.editText!!.text.toString(),
                origin = inputOrigin.editText!!.text.toString(),
                episodes = inputEpisodes.editText!!.text.toString().toInt(),
            ))
        }
    }

    private fun getCharacter() {
        lifecycleScope.launchWhenStarted {
            val localCharacter = database.characterDao().getCharacter(args.id)
            if (localCharacter == null) {
                Toast.makeText(requireContext(), getString(R.string.error_character_not_found), Toast.LENGTH_LONG).show()
                requireActivity().onBackPressed()
            } else {
                character = localCharacter
                setData()
            }
        }
    }

    private fun fetchCharacter() {
        RetrofitInstance.api.getCharacter(args.id).enqueue(object: Callback<CharacterDto> {
            override fun onResponse(call: Call<CharacterDto>, response: Response<CharacterDto>) {
                if (response.isSuccessful && response.body() != null) {
                    character = response.body()!!.mapToModel()
                    updateCharacter(character)
                    setData()
                } else {
                    Toast.makeText(requireContext(), getString(R.string.error_character_not_found), Toast.LENGTH_LONG).show()
                    requireActivity().onBackPressed()
                }
            }

            override fun onFailure(call: Call<CharacterDto>, t: Throwable) {
                Toast.makeText(requireContext(), getString(R.string.error_fetching), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun updateCharacter(character: Character) {
        lifecycleScope.launch {
            database.characterDao().update(character)
            Toast.makeText(requireContext(), getString(R.string.successful_update), Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteCharacter() {
        lifecycleScope.launch {
            if (database.characterDao().delete(character) > 0) {
                Toast.makeText(requireContext(), getString(R.string.successful_delete), Toast.LENGTH_LONG).show()
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(requireContext(), "Error eliminando personaje, inténtalo de nuevo", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setData() {
        character.apply {
            inputName.editText!!.setText(name)
            inputSpecies.editText!!.setText(species)
            inputStatus.editText!!.setText(status)
            inputGender.editText!!.setText(gender)
            inputOrigin.editText!!.setText(origin)
            inputEpisodes.editText!!.setText(episodes.toString())
            imageCharacter.load(image) {
                placeholder(R.drawable.ic_downloading)
                transformations(CircleCropTransformation())
                error(R.drawable.ic_error)
                diskCachePolicy(CachePolicy.ENABLED)
            }
        }
    }

}