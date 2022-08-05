package com.durini.frontendbasico

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnToast: MaterialButton
    private lateinit var btnWidgets: MaterialButton
    private lateinit var btnLinearLayout: MaterialButton
    private lateinit var btnRelativeLayout: MaterialButton
    private lateinit var btnConstraintLayout: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos en onCreate, ya que aquí ya tenemos acceso a la UI
        // asegúrense de poner esto abajo de setContentView, porque si lo ponen arriba,
        // les va a tronar el app (porque setContentView inicializa la UI)
        btnToast = findViewById(R.id.btn_mainActivity_toast)
        btnWidgets = findViewById(R.id.btn_mainActivity_widgets)
        btnLinearLayout = findViewById(R.id.btn_mainActivity_linearLayout)
        btnRelativeLayout = findViewById(R.id.btn_mainActivity_relativeLayout)
        btnConstraintLayout = findViewById(R.id.btn_mainActivity_constraintLayout)

        initListeners()
    }

    private fun initListeners() {
        btnToast.setOnClickListener {
            // Siempre deben hacer ".show" para que se muestre
            Toast.makeText(this, "Hola mundo!", Toast.LENGTH_LONG).show()
        }
        btnWidgets.setOnClickListener {
            startActivity(Intent(this, WidgetsActivity::class.java))
        }

        btnLinearLayout.setOnClickListener {
            startActivity(Intent(this, LinearLayoutActivity::class.java))
        }

        btnRelativeLayout.setOnClickListener {
            startActivity(Intent(this, RelativeLayoutActivity::class.java))
        }

        btnConstraintLayout.setOnClickListener {
            startActivity(Intent(this, ConstraintLayoutActivity::class.java))
        }
    }
}