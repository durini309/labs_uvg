package com.durini.hilt.presentation

import androidx.lifecycle.ViewModel
import com.durini.hilt.data.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MyRepository
) : ViewModel() {

    // Función creada sólo para que el activity acceda al viewmodel
    fun test() {
    }
}