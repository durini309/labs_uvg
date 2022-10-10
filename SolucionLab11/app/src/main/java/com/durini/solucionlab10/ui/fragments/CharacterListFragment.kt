package com.durini.solucionlab10.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.durini.solucionlab10.R
import com.durini.solucionlab10.data.local.LabDatabase
import com.durini.solucionlab10.data.local.model.Character
import com.durini.solucionlab10.data.remote.api.RetrofitInstance
import com.durini.solucionlab10.data.remote.dto.CharacterDto
import com.durini.solucionlab10.data.remote.dto.CharactersResponse
import com.durini.solucionlab10.data.remote.dto.mapToModel
import com.durini.solucionlab10.ui.KEY_EMAIL
import com.durini.solucionlab10.ui.adapters.CharacterAdapter
import com.durini.solucionlab10.ui.dataStore
import com.durini.solucionlab10.ui.removePreferencesValue
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListFragment : Fragment(R.layout.fragment_character_list), CharacterAdapter.RecyclerViewCharactersEvents {

    private lateinit var adapter: CharacterAdapter
    private lateinit var toolbar: MaterialToolbar
    private lateinit var recyclerCharacters: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var database: LabDatabase

    private val characters: MutableList<Character> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCharacters = view.findViewById(R.id.recycler_characters)
        toolbar = view.findViewById(R.id.toolbar_characterList)
        progressBar = view.findViewById(R.id.progress_characters)
        database = Room.databaseBuilder(
            requireContext(),
            LabDatabase::class.java,
            "labDatabase"
        ).build()

        setToolbar()
        setListeners()
        getCharacters()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setToolbar() {
        val navController = findNavController()
        val appbarConfig = AppBarConfiguration(setOf(R.id.characterListFragment))

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

                R.id.menu_item_logout -> {
                    logout()
                    true
                }

                R.id.menu_item_sync -> {
                    fetchCharacters(isSync = true)
                    true
                }
                else -> true
            }
        }
    }

    private fun getCharacters() {
        lifecycleScope.launchWhenStarted {
            val characters = database.characterDao().getCharacters()
            if (characters.isEmpty()) {
                fetchCharacters(isSync = false)
            } else {
                showCharacters(characters, false)
            }
        }
    }

    private fun fetchCharacters(isSync: Boolean) {
        RetrofitInstance.api.getCharacters().enqueue(object: Callback<CharactersResponse> {
            override fun onResponse(
                call: Call<CharactersResponse>,
                response: Response<CharactersResponse>
            ) {
                if (response.isSuccessful) {
                    val res = response.body()?.results
                    saveCharactersLocally(res ?: mutableListOf(), isSync)
                    if (isSync) {
                        Toast.makeText(requireContext(), getString(R.string.successful_list_update), Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                Toast.makeText(requireContext(), getString(R.string.error_fetching), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun showCharacters(characters: List<Character>, isSync: Boolean) {
        progressBar.visibility = View.GONE
        recyclerCharacters.visibility = View.VISIBLE
        this.characters.clear()
        this.characters.addAll(characters)

        if (!isSync) {
            setupRecycler()
        } else {
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupRecycler() {
        adapter = CharacterAdapter(this.characters, this)
        recyclerCharacters.layoutManager = LinearLayoutManager(requireContext())
        recyclerCharacters.setHasFixedSize(true)
        recyclerCharacters.adapter = adapter
    }

    private fun saveCharactersLocally(characters: List<CharacterDto>, isSync: Boolean) {
        lifecycleScope.launch {
            val charactersToStore = characters.map { characterDto -> characterDto.mapToModel() }
            database.characterDao().insertAll(charactersToStore)
            showCharacters(charactersToStore, isSync)
        }
    }

    private fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            requireContext().dataStore.removePreferencesValue(KEY_EMAIL)
            CoroutineScope(Dispatchers.Main).launch {
                requireView().findNavController().navigate(
                    CharacterListFragmentDirections.actionCharacterListFragmentToLoginFragment()
                )
            }
        }
    }

    override fun onItemClicked(character: Character) {
        val action = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(
            character.id
        )

        requireView().findNavController().navigate(action)
    }

}