package com.durini.corecomponents

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class PermissionsActivity : AppCompatActivity() {

    private lateinit var buttonCamera: MaterialButton
    private lateinit var buttonExternal: MaterialButton
    private lateinit var rootLayout: ConstraintLayout

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        rootLayout = findViewById(R.id.root_permissionActivity)
        buttonCamera = findViewById(R.id.button_permissionActivity_camera)
        buttonExternal = findViewById(R.id.button_permissionActivity_writeExternal)

        setListeners()
    }

    /**
     * Método que ya existe en el activity, pero debemos hacer override para saber si el usuario
     * aceptó o rechazó los permisos. Realmente este método no siempre nos va a servir, pero
     * si quieren hacer algo inmendiatamente despues de que se acepto o rechazo un permiso,
     * lo podrían hacer aqui
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, // El codigo que mandamos nosotros en el metodo requestPermissions
        permissions: Array<out String>, // Estos son los permisos que solicitamos
        grantResults: IntArray // Aqui vienen los valores (GRANTED o DENIED) de los N permisos que solicitamos
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Usamos el requestCode que enviamos en requestPermissions, en nuestro caso fue 0
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("TAG", "${permissions[i]} was granted")
                }
            }
        }
    }

    private fun setListeners() {
        buttonCamera.setOnClickListener {
            checkCameraPermission()
        }
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
                    rootLayout,
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