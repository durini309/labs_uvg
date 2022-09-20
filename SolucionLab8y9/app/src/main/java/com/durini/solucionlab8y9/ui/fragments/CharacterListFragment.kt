package com.durini.solucionlab8y9.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durini.solucionlab8y9.R
import com.durini.solucionlab8y9.datasource.api.RetrofitInstance
import com.durini.solucionlab8y9.datasource.model.Character
import com.durini.solucionlab8y9.datasource.model.CharactersResponse
import com.durini.solucionlab8y9.ui.adapters.CharacterAdapter
import com.google.android.material.appbar.MaterialToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListFragment : Fragment(R.layout.fragment_character_list), CharacterAdapter.RecyclerViewCharactersEvents {

    private lateinit var characters: MutableList<Character>
    private lateinit var adapter: CharacterAdapter
    private lateinit var toolbar: MaterialToolbar
    private lateinit var recyclerCharacters: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCharacters = view.findViewById(R.id.recycler_characters)
        toolbar = view.findViewById(R.id.toolbar_characterList)

        setToolbar()
        setListeners()
        getCharacters()
    }

    private fun setToolbar() {
        val navController = findNavController()
        val appbarConfig = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appbarConfig)
    }

    private fun setListeners() {
        toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_item_asc -> {
                    characters.sortBy { character -> character.name }
                    adapter.notifyDataSetChanged()
                    true
                }

                R.id.menu_item_des -> {
                    characters.sortByDescending { character -> character.name }
                    adapter.notifyDataSetChanged()
                    true
                }
                else -> true
            }
        }
    }

    private fun getCharacters() {
        RetrofitInstance.api.getCharacters().enqueue(object: Callback<CharactersResponse> {
            override fun onResponse(
                call: Call<CharactersResponse>,
                response: Response<CharactersResponse>
            ) {
                if (response.isSuccessful) {
                    val res = response.body()?.results
                    setupRecycler(res ?: mutableListOf())
                }
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                Toast.makeText(requireContext(), getString(R.string.error_fetching), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setupRecycler(characters: MutableList<Character>) {

        this.characters = characters

        adapter = CharacterAdapter(this.characters, this)
        recyclerCharacters.layoutManager = LinearLayoutManager(requireContext())
        recyclerCharacters.setHasFixedSize(true)
        recyclerCharacters.adapter = adapter
    }

    override fun onItemClicked(character: Character) {
        val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(
            character.id.toInt()
        )

        requireView().findNavController().navigate(action)
    }

}