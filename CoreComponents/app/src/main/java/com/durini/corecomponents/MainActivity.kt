package com.durini.corecomponents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.durini.corecomponents.data.User
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var buttonIncrement: Button
    private lateinit var buttonIntents: Button
    private lateinit var buttonPermissions: Button
    private lateinit var buttonCreate: Button
    private lateinit var textCounter: TextView
    private lateinit var inputName: TextInputLayout
    private lateinit var inputLastname: TextInputLayout

    private var counter = 0
    private val LOG_TAG = "CoreComponents"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(LOG_TAG, "onCreate")

        buttonIncrement = findViewById(R.id.button_mainActivity_increment)
        buttonIntents = findViewById(R.id.button_mainActivity_intents)
        buttonPermissions = findViewById(R.id.button_mainActivity_permissions)
        buttonCreate = findViewById(R.id.button_mainActivity_createUser)
        textCounter = findViewById(R.id.text_mainActivity_counter)
        inputName = findViewById(R.id.textLayout_mainActivity_name)
        inputLastname = findViewById(R.id.textLayout_mainActivity_lastname)
        textCounter.text = counter.toString()
        setListeners()
    }

    override fun onStart() {
        Log.d(LOG_TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(LOG_TAG, "onResume")
        super.onResume()
    }

    override fun onRestart() {
        Log.d(LOG_TAG, "onRestart")
        super.onRestart()
    }

    override fun onPause() {
        Log.d(LOG_TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(LOG_TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy")
        super.onDestroy()
    }

    private fun setListeners() {
        buttonIncrement.setOnClickListener {
            counter++
            textCounter.text = counter.toString()
        }

        buttonCreate.setOnClickListener {
            val name = inputName.editText?.text.toString()
            val lastname = inputLastname.editText?.text.toString()
            val fullName = "$name $lastname"
            val user = User(name, lastname)

            val intent = Intent(this, UserActivity::class.java)

            // Pueden mandar un primitivo. En este caso, un String
            intent.putExtra("EXTRA_FULLNAME", fullName)
            // O pueden mandar un objeto que extienda de la clase Serializable
            intent.putExtra("EXTRA_USER", user)
            startActivity(intent)
        }

        buttonIntents.setOnClickListener {
            startActivity(Intent(this, IntentsActivity::class.java))
        }

        buttonPermissions.setOnClickListener {
            startActivity(Intent(this, PermissionsActivity::class.java))
        }
    }
}