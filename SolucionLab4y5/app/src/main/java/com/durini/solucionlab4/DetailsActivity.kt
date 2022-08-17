package com.durini.solucionlab4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.durini.solucionlab4.data.Place
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {

    private lateinit var textPlaceName: TextView
    private lateinit var textPlaceAddress: TextView
    private lateinit var buttonStart: Button
    private lateinit var textPlaceSchedule: TextView
    private lateinit var textPlacePhone: TextView
    private lateinit var buttonCallPlace: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        textPlaceName = findViewById(R.id.text_detailsActivity_placeName)
        textPlaceAddress = findViewById(R.id.text_detailsActivity_placeAddress)
        textPlaceSchedule = findViewById(R.id.text_detailsActivity_schedule)
        textPlacePhone = findViewById(R.id.text_detailsActivity_phone)
        buttonStart = findViewById(R.id.button_detailsActivity_start)
        buttonCallPlace = findViewById(R.id.button_detailsActivity_call)

        val place = intent.getSerializableExtra(EXTRA_PLACE) as Place
        showPlaceInfo(place)
        setListeners(place)
    }

    private fun setListeners(place: Place) {
        buttonStart.setOnClickListener {
            checkCameraPermission()
        }

        buttonCallPlace.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${place.phone}"))
            startActivity(intent)
        }
    }

    private fun showPlaceInfo(place: Place) {
        textPlaceName.text = place.name
        textPlaceAddress.text = place.address
        textPlaceSchedule.text = place.schedule
        textPlacePhone.text = place.phone
    }

    // Método que retorna true en caso el usuario ya haya aceptado el permiso. False si no.
    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun checkCameraPermission() {
        if (!hasCameraPermission()) {
            checkRequestRationale()
        } else {
            Toast.makeText(this, "Permiso otorgadp, abrir camara", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkRequestRationale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Verificamos si debemos mostrar algun mensale racional
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {

                // En mi caso, lo mostraré en un SnackBar. Asi crean uno básico
                val snackbar = Snackbar.make(
                    buttonStart,
                    "Acceso a cámara es necesario para poder tomar fotos",
                    Snackbar.LENGTH_INDEFINITE
                )
                // Así pueden asignarle una acción (no es obligatorio, pero para nuestro caso sí lo será)
                snackbar.setAction("Ok"){
                    // Método a ejecutar al apachar el botón del snackbar
                    requestCameraPermission()
                }
                // Y por último, lo deben mostrar
                snackbar.show()
            } else {
                requestCameraPermission()
            }
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this, // Siempre deben poner el contexto del activity
            arrayOf(Manifest.permission.CAMERA), // La lista con 1 o mas permisos a solicitar
            0 // Codigo que usan en onRequestPermissionsResult
        )
    }


}