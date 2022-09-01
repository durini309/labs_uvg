package com.durini.frontendavanzado.activities

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.ImageView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.durini.frontendavanzado.R

class ImageLoaderActivity : AppCompatActivity() {
    private lateinit var buttonImage1: Button
    private lateinit var buttonImage2: Button
    private lateinit var buttonImage3: Button
    private lateinit var buttonImage4: Button
    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView
    private lateinit var image4: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_loader)

        buttonImage1 = findViewById(R.id.button_imageLoaderActivity_image1)
        buttonImage2 = findViewById(R.id.button_imageLoaderActivity_image2)
        buttonImage3 = findViewById(R.id.button_imageLoaderActivity_image3)
        buttonImage4 = findViewById(R.id.button_imageLoaderActivity_image4)
        image1 = findViewById(R.id.image_imageLoaderActivity_1)
        image2 = findViewById(R.id.image_imageLoaderActivity_2)
        image3 = findViewById(R.id.image_imageLoaderActivity_3)
        image4 = findViewById(R.id.image_imageLoaderActivity_4)

        setListeners()
    }

    private fun setListeners() {
        // Cargamos la imagen con placeholder e ícono de error incluidos
        buttonImage1.setOnClickListener {
            image1.load("https://rickandmortyapi.com/api/character/avatar/1.jpeg") {
                error(R.drawable.ic_error)
                placeholder(R.drawable.ic_downloading)
            }
        }

        // Cargamos la imagen con animación al aparecer y con una transformación circular
        buttonImage2.setOnClickListener {
            image2.load("https://rickandmortyapi.com/api/character/avatar/2.jpeg") {
                crossfade(true)
                placeholder(R.drawable.ic_downloading)
                error(R.drawable.ic_error)
                transformations(CircleCropTransformation())
            }
        }

        // Imagen con custom transformation
        buttonImage3.setOnClickListener {
            image3.load("https://rickandmortyapi.com/api/character/avatar/4.jpeg") {
                placeholder(R.drawable.ic_downloading)
                error(R.drawable.ic_error)
                transformations(RoundedCornersTransformation(20.toPx, 20.toPx, 20.toPx, 20.toPx))
            }
        }

        // Imagen guardada en cache
        buttonImage4.setOnClickListener {
            image4.load("https://rickandmortyapi.com/api/character/avatar/5.jpeg") {
                placeholder(R.drawable.ic_downloading)
                error(R.drawable.ic_error)
                diskCachePolicy(CachePolicy.ENABLED)
            }
        }
    }
}

val Number.toPx get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics)