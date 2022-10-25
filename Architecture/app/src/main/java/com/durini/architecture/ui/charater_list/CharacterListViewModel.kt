package com.durini.architecture.ui.charater_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.durini.architecture.data.Resource
import com.durini.architecture.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<CharacterListUiState> =
        MutableStateFlow(CharacterListUiState.Default)
    val uiState: StateFlow<CharacterListUiState> = _uiState

    fun getCharacters() {
        viewModelScope.launch {
            _uiState.value = CharacterListUiState.Loading
            when (val charactersResult = repository.getCharacters()) {
                is Resource.Error -> {
                    _uiState.value = CharacterListUiState.Error(charactersResult.message ?: "")
                }
                is Resource.Success -> {
                    _uiState.value = CharacterListUiState.Success(charactersResult.data ?: listOf())
                }
            }
        }
    }

}