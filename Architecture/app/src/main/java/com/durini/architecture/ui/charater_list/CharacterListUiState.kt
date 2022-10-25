package com.durini.architecture.ui.charater_list

import com.durini.architecture.data.local.entity.Character

sealed interface CharacterListUiState {
    data class Success(val characters: List<Character>): CharacterListUiState
    object Loading: CharacterListUiState
    object Default: CharacterListUiState
    data class Error(val message: String): CharacterListUiState
}