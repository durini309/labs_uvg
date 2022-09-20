package com.durini.keyvaluestorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {

    private lateinit var inputKey: TextInputLayout
    private lateinit var inputValue: TextInputLayout
    private lateinit var buttonSave: Button
    private lateinit var inputSearchKey: TextInputLayout
    private lateinit var buttonGet: Button
    private lateinit var textValue: TextView

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)

        inputKey = findViewById(R.id.inputLayout_dataStore_key)
        inputValue = findViewById(R.id.inputLayout_dataStore_value)
        buttonSave = findViewById(R.id.button_dataStore_saveData)
        inputSearchKey = findViewById(R.id.inputLayout_dataStore_searchKey)
        buttonGet = findViewById(R.id.button_dataStore_getData)
        textValue = findViewById(R.id.text_dataStore_foundValue)

        setListeners()
    }

    private fun setListeners() {
        buttonSave.setOnClickListener {
            val key = inputKey.editText!!.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                saveKeyValue(
                    key = key,
                    value = inputValue.editText!!.text.toString()
                )

                CoroutineScope(Dispatchers.Main).launch {
                    inputKey.editText!!.text.clear()
                    inputValue.editText!!.text.clear()

                    Toast.makeText(
                        applicationContext,
                        "Llave '$key' guardada",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        buttonGet.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val value = getValueFromKey(
                    key = inputSearchKey.editText!!.text.toString(),
                )

                CoroutineScope(Dispatchers.Main).launch {
                    textValue.text = getString(R.string.found_value).format(value)
                }

            }
        }
    }

    private suspend fun saveKeyValue(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun getValueFromKey(key: String) : String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}