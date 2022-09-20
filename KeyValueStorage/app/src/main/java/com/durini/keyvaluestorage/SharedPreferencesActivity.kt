package com.durini.keyvaluestorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class SharedPreferencesActivity : AppCompatActivity() {

    private lateinit var inputKey: TextInputLayout
    private lateinit var inputValue: TextInputLayout
    private lateinit var buttonSave: Button
    private lateinit var inputSearchKey: TextInputLayout
    private lateinit var buttonGet: Button
    private lateinit var textValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        inputKey = findViewById(R.id.inputLayout_sharedPreferences_key)
        inputValue = findViewById(R.id.inputLayout_sharedPreferences_value)
        buttonSave = findViewById(R.id.button_sharedPreferences_saveData)
        inputSearchKey = findViewById(R.id.inputLayout_sharedPreferences_searchKey)
        buttonGet = findViewById(R.id.button_sharedPreferences_getData)
        textValue = findViewById(R.id.text_sharedPreferences_foundValue)

        setListeners()
    }

    private fun setListeners() {
        buttonSave.setOnClickListener {
            val key = inputKey.editText!!.text.toString()
            saveKeyValue(
                key = key,
                value = inputValue.editText!!.text.toString()
            )
            inputKey.editText!!.text.clear()
            inputValue.editText!!.text.clear()
            Toast.makeText(
                this,
                "Llave '$key' guardada",
                Toast.LENGTH_LONG
            ).show()
        }

        buttonGet.setOnClickListener {
            textValue.text = getString(R.string.found_value).format(getValueFromKey(
                key = inputSearchKey.editText!!.text.toString()
            ))
        }
    }

    private fun saveKeyValue(key: String, value: String) {
        val sharedPref = this.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            putString(key, value)
            apply()
        }
    }

    private fun getValueFromKey(key: String) : String {
        val sharedPref = this.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        return sharedPref.getString(key, getString(R.string.key_doesnt_exist)) ?: "null"
    }
}