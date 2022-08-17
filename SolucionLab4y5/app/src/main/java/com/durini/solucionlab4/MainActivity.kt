package com.durini.solucionlab4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.durini.solucionlab4.data.Place

class MainActivity : AppCompatActivity() {

    private lateinit var buttonDownload: Button
    private lateinit var textPlaceName: TextView
    private lateinit var textPlaceAddress: TextView
    private lateinit var textPlaceSchedule: TextView
    private lateinit var imageDirection: ImageView
    private lateinit var buttonStart: Button
    private lateinit var buttonDetails: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDownload = findViewById(R.id.button_activityMain_download)
        textPlaceName = findViewById(R.id.text_activityMain_placeName)
        textPlaceAddress = findViewById(R.id.text_activityMain_placeAddress)
        textPlaceSchedule = findViewById(R.id.text_activityMain_placeSchedule)
        imageDirection = findViewById(R.id.image_activityMain_directions)
        buttonStart = findViewById(R.id.button_activityMain_start)
        buttonDetails = findViewById(R.id.button_activityMain_details)

        setListeners()
    }

    private fun setListeners() {
        buttonDownload.setOnClickListener {
            val packageName = "com.whatsapp"
            try {
                // Probamos abrir playstore
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")
                    )
                )
            } catch (ex: Exception) {
                // Si no está instalada, abrimos web browser
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }

        buttonStart.setOnClickListener {
            Toast.makeText(this, "Juan Carlos Durini", Toast.LENGTH_LONG).show()
        }

        buttonDetails.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            val place = Place(
                name = textPlaceName.text.toString(),
                address = textPlaceAddress.text.toString(),
                schedule = textPlaceSchedule.text.toString(),
                phone = "23331524"
            )
            intent.putExtra(EXTRA_PLACE, place)
            startActivity(intent)
        }

        imageDirection.setOnClickListener{
            val location = "http://maps.google.com/maps?q=loc:14.603835110728907,-90.48925677205315"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))
            startActivity(intent)
        }
    }
}