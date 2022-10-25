package com.durini.architecture.ui.charater_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.durini.architecture.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservables()
        viewModel.getCharacters()
    }

    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { state ->
                handleState(state)
            }
        }
    }

    private fun handleState(state: CharacterListUiState) {
        when(state) {
            CharacterListUiState.Default -> {
                binding.progressCharacters.visibility = View.GONE
                binding.recyclerCharacters.visibility = View.GONE
            }
            is CharacterListUiState.Error -> {
                binding.progressCharacters.visibility = View.GONE
                binding.recyclerCharacters.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    state.message,
                    Toast.LENGTH_LONG
                ).show()
            }
            CharacterListUiState.Loading -> {
                binding.progressCharacters.visibility = View.VISIBLE
                binding.recyclerCharacters.visibility = View.GONE
            }
            is CharacterListUiState.Success -> {
                println(state.characters)
                binding.progressCharacters.visibility = View.GONE
                binding.recyclerCharacters.visibility = View.VISIBLE
                // initRecyclerview()
            }
        }
    }
}