package com.durini.solucionlab12.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val TOKEN_DURATION = 5000L
private const val LOGIN_TIME = 2000L
class SessionViewModel : ViewModel() {

    sealed interface LoginState {
        object Default: LoginState
        object Success: LoginState
        object Loading: LoginState
        class Error(val message: String): LoginState
    }

    private val _validAuthToken = MutableStateFlow(false)
    val validAuthToken: StateFlow<Boolean> = _validAuthToken

    private val _loginStatus = MutableStateFlow<LoginState>(LoginState.Default)
    val loginStatus: StateFlow<LoginState> = _loginStatus

    private lateinit var job: Job


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginStatus.value = LoginState.Loading
            delay(LOGIN_TIME)
            if (email == "jcdurini@uvg.edu.gt" && email == password) {
                _loginStatus.value = LoginState.Success
            } else {
                _loginStatus.value = LoginState.Error("Credenciales incorrectas")
            }

            _loginStatus.value = LoginState.Default
        }
    }

    fun generateToken() {
        job = viewModelScope.launch {
            _validAuthToken.value = true
            delay(TOKEN_DURATION)
            _validAuthToken.value = false
        }
    }

    fun cancelJob() {
        job.cancel()
    }

    fun logOut() {
        job.cancel()
        _validAuthToken.value = false
    }

}