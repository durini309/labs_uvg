package com.durini.solucionlab10.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.durini.solucionlab10.R
import com.durini.solucionlab10.datasource.api.RetrofitInstance
import com.durini.solucionlab10.datasource.model.Character
import com.google.android.material.appbar.MaterialToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private val args: CharacterDetailsFragmentArgs by navArgs()
    private lateinit var txtName: TextView
    private lateinit var txtSpecies: TextView
    private lateinit var txtGender: TextView
    private lateinit var txtStatus: TextView
    private lateinit var txtOrigin: TextView
    private lateinit var txtEpisodes: TextView
    private lateinit var imageCharacter: ImageView
    private lateinit var toolbar: MaterialToolbar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            txtName = findViewById(R.id.text_characterDetails_name)
            txtSpecies = findViewById(R.id.text_characterDetails_species)
            txtGender = findViewById(R.id.text_characterDetails_gender)
            txtStatus = findViewById(R.id.text_characterDetails_status)
            txtOrigin = findViewById(R.id.text_characterDetails_origin)
            txtEpisodes = findViewById(R.id.text_characterDetails_episodes)
            imageCharacter = findViewById(R.id.image_characterDetails)
            toolbar = findViewById(R.id.toolbar_characterDetails)
        }

        setToolbar()
        getCharacter()
    }

    private fun setToolbar() {
        val navController = findNavController()
        val appbarConfig = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appbarConfig)
    }

    private fun getCharacter() {
        RetrofitInstance.api.getCharacter(args.id).enqueue(object: Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful && response.body() != null) {
                    setData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Toast.makeText(requireContext(), getString(R.string.error_fetching), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setData(character: Character) {
        character.apply {
            txtName.text = name
            txtSpecies.text = species
            txtStatus.text = status
            txtGender.text = gender
            txtOrigin.text = origin.name
            txtEpisodes.text = episode.size.toString()
            imageCharacter.load(image) {
                placeholder(R.drawable.ic_downloading)
                transformations(CircleCropTransformation())
                error(R.drawable.ic_error)
                memoryCachePolicy(CachePolicy.ENABLED)
            }
        }
    }

}