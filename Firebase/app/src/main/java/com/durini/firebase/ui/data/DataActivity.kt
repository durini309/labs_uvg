package com.durini.firebase.ui.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.durini.firebase.R
import com.durini.firebase.data.local.entity.Place
import com.durini.firebase.data.remote.api.PlaceApi
import com.durini.firebase.data.repository.place.PlaceRepository
import com.durini.firebase.databinding.ActivityDataBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBinding
    private lateinit var userId: String

    @Inject
    lateinit var placeRepository: PlaceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        userId = intent.getStringExtra("id") ?: ""
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            buttonInsert.setOnClickListener {
                val internalId = inputInsertRecordId.editText!!.text.toString()
                val placeName = inputInsertRecordPlace.editText!!.text.toString()
                val maskMandatory = switchInsertRecordMask.isChecked

                lifecycleScope.launch(Dispatchers.IO) {
                    placeRepository.createPlace(
                        place = Place(
                            id = internalId.toInt(),
                            name = placeName,
                            isMaskMandatory = maskMandatory
                        ),
                        owner = userId
                    )
                }
            }

            buttonGetByID.setOnClickListener {
                val id = inputGetRecordId.editText!!.text.toString()

                lifecycleScope.launch(Dispatchers.IO){
                    val place = placeRepository.getPlace(id)

                    lifecycleScope.launch(Dispatchers.Main) {
                        textRecord.text = place?.toString() ?: "No encontrado"
                    }
                }
            }

            buttonGetByMask.setOnClickListener {
                val maskMandatory = switchGetRecordMask.isChecked

                lifecycleScope.launch(Dispatchers.IO){
                    val places = placeRepository.getPlacesFilteredByMaskUsage(maskMandatory)

                    lifecycleScope.launch(Dispatchers.Main) {
                        textRecord.text = places?.toString() ?: "Sin resultados"
                    }
                }
            }
        }
    }
}