package com.durini.firebase.data.remote.firestore

import android.util.Log
import com.durini.firebase.data.remote.api.PlaceApi
import com.durini.firebase.data.remote.dto.PlaceDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class FirestorePlaceApiImpl(
    private val db: FirebaseFirestore
) : PlaceApi {
    override suspend fun insert(place: PlaceDto) {
        // Crea un nuevo documento con un ID autogenerado
        db.collection("place")
            .add(place)
            .await()
    }

    override suspend fun getById(id: String): PlaceDto? {
        val document = db.collection("place").document(id).get().await()
        return document?.toObject<PlaceDto>()
    }

    override suspend fun getByMaskUsage(isMaskMandatory: Boolean): List<PlaceDto>? {
        return try {
            val res = db
                .collection("place")
                .whereEqualTo("maskMandatory", isMaskMandatory)
                .get()
                .await()

            res?.documents?.map { documentSnapshot ->
                documentSnapshot.toObject<PlaceDto>()!!
            }
        } catch (e: Exception) {
            Log.e("Firebase error", e.toString())
            return null
        }

    }
}