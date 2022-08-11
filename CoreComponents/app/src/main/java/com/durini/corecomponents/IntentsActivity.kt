package com.durini.corecomponents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton


class IntentsActivity : AppCompatActivity() {

    private lateinit var buttonEmail: MaterialButton
    private lateinit var buttonCall: MaterialButton
    private lateinit var buttonShare: MaterialButton
    private lateinit var buttonNavigate: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)

        buttonEmail = findViewById(R.id.button_intentActivity_sendEmail)
        buttonCall = findViewById(R.id.button_intentActivity_call)
        buttonShare = findViewById(R.id.button_intentActivity_share)
        buttonNavigate = findViewById(R.id.button_intentActivity_navigate)

        setListeners()
    }

    private fun setListeners() {
        buttonEmail.setOnClickListener {
            // Creamos un intent implicito. Cualquier app que sepa como manejar esto, saldrá como opción
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:your_emailid@uvg.edu.gt"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject")
            intent.putExtra(Intent.EXTRA_TEXT, "your_text")
            startActivity(intent)
        }

        buttonCall.setOnClickListener {
            // Tambien es un intent implicito
            val phoneNum = "+50223231524"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            startActivity(intent)
        }

        buttonShare.setOnClickListener {
            // Intent explicito. Estamos pidiendo que nos muestre el chooser nativo del SO
            val intent = Intent()
            intent.type = "text/plain"
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Lorem ipsum dolor sit amet...")
            startActivity(Intent.createChooser(intent, null))
        }

        buttonNavigate.setOnClickListener {
            // Explicito. Estamos pidiendo que use google maps
            val location = "http://maps.google.com/maps?q=loc:14.603835110728907,-90.48925677205315"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))
            startActivity(intent)
        }
    }
}