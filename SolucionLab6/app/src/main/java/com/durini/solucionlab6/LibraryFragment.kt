package com.durini.solucionlab6

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class LibraryFragment : Fragment(R.layout.fragment_library) {

    private lateinit var imageAdd: ImageView
    private lateinit var textLikedSongs: TextView
    private var likedSongsCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageAdd = view.findViewById(R.id.image_libraryFragment_add)
        textLikedSongs = view.findViewById(R.id.text_libraryFragment_likedSongsCount)
        setListeners()
        updateLikedSongs()
    }

    private fun setListeners() {
        imageAdd.setOnClickListener {
            likedSongsCount++
            updateLikedSongs()
        }
    }

    private fun updateLikedSongs() {
        textLikedSongs.text = getString(R.string.liked_songs_counter).format(likedSongsCount)
    }

}