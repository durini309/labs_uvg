package com.durini.solucionlab12.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val STATE_LOADING_TIME = 2000L

class HomeViewModel : ViewModel() {

    sealed class HomeViewState {
        object Default: HomeViewState()
        object Success: HomeViewState()
        object Failure: HomeViewState()
        object Empty: HomeViewState()
        class Loading(val currentState: HomeViewState = HomeViewState.Default): HomeViewState()
    }

    private val _homeState = MutableStateFlow<HomeViewState>(HomeViewState.Default)
    val homeState: StateFlow<HomeViewState> = _homeState

    fun changeState(state: HomeViewState) {
        viewModelScope.launch {
            _homeState.value = HomeViewState.Loading(currentState = state)
            delay(STATE_LOADING_TIME)
            _homeState.value = state
        }
    }
}