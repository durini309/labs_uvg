package com.durini.viewmodel.observables

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ObservableViewModel : ViewModel() {

    private val _liveData: MutableLiveData<String> = MutableLiveData()
    val liveData: LiveData<String> = _liveData

    private val _stateFlow: MutableStateFlow<String> = MutableStateFlow("")
    val stateFlow: StateFlow<String> = _stateFlow


    fun triggerLiveData() {
        _liveData.value = "LiveData"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "StateFlow"

        // También podemos actualizar el valor múltiples veces, parecido a un flow
        // Lo puse en una Coroutine para hacer delay entre cambio de values
        viewModelScope.launch {
            delay(2000L)
            _stateFlow.value = "StateFlow 1"
            delay(2000L)
            _stateFlow.value = "StateFlow 2"
        }
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            emit("Downloading data ...")
            delay(2000L)
            emit("Processing data ...")
            delay(2000L)
            emit("Data downloaded!")
        }
    }
}