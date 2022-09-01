package com.durini.frontendavanzado.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.durini.frontendavanzado.R
import com.durini.frontendavanzado.entities.Place
import com.durini.frontendavanzado.entities.PlaceType
import java.util.*

class PlaceAdapter(
    private val dataSet: MutableList<Place>,
    private val placeListener: PlaceListener
):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    interface PlaceListener {
        fun onPlaceClicked(place: Place, position: Int)
    }

    class ViewHolder(private val view: View,
                     private val listener: PlaceListener) : RecyclerView.ViewHolder(view) {
        private val imageType: ImageView = view.findViewById(R.id.image_itemPlace_category)
        private val textName: TextView = view.findViewById(R.id.text_itemPlace_name)
        private val layout: ConstraintLayout = view.findViewById(R.id.layout_itemPlace)
        private lateinit var place: Place

        fun setData(place: Place) {
            this.place = place
            textName.text = place.name
            imageType.setImageDrawable(
                ContextCompat.getDrawable(view.context, getTypeIcon(place.type))
            )
            setListeners()
        }

        private fun setListeners() {
            layout.setOnClickListener {
                listener.onPlaceClicked(place, this.adapterPosition)
            }
        }

        private fun getTypeIcon(placeType: PlaceType) : Int = when(placeType) {
            PlaceType.RESTAURANT -> R.drawable.ic_restaurant
            PlaceType.BAR -> R.drawable.ic_bar
            PlaceType.PARK -> R.drawable.ic_park
            PlaceType.SHOPPING_MALL -> R.drawable.ic_shopping_mall
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_place, parent, false)

        return ViewHolder(view, placeListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}