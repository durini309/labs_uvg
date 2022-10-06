package com.durini.viewmodel.scope

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SecondViewModel : ViewModel() {

    private lateinit var job: Job
    private val _checkInStatus = MutableStateFlow<CheckInStatus>(CheckInStatus.Missing)
    val checkInStatus: StateFlow<CheckInStatus> = _checkInStatus

    sealed class CheckInStatus {
        object Missing: CheckInStatus()
        object Loading: CheckInStatus()
        class Success(val message: String): CheckInStatus()
        class Failure(val errorMessage: String): CheckInStatus()
    }

    fun checkIn() {
        job = viewModelScope.launch {
            _checkInStatus.value = CheckInStatus.Loading
            delay(10000L)
            // Si es par, va a ser exitoso.
            if ((0..50).random() % 2 == 0) {
                _checkInStatus.value = CheckInStatus.Success("Check in exitoso")
            } else {
                _checkInStatus.value = CheckInStatus.Failure("Error inesperado")
            }
        }
    }

    fun reset() {
        if (this::job.isInitialized && job.isActive) {
            job.cancel()
            _checkInStatus.value = CheckInStatus.Missing
        }
    }



}