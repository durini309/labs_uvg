package com.durini.roomdemo.presentation.user_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.durini.roomdemo.R
import com.durini.roomdemo.domain.model.User

class UserAdapter(
    private val dataSet: List<User>,
    private val listeners: UserClickListeners
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View, private val listeners: UserClickListeners): RecyclerView.ViewHolder(view) {

        private val txtId: TextView = view.findViewById(R.id.text_user_id)
        private val txtName: TextView = view.findViewById(R.id.text_user_name)
        private val txtCountry: TextView = view.findViewById(R.id.text_user_country)
        private val txtAge: TextView = view.findViewById(R.id.text_user_age)
        private val iconEdit: ImageView = view.findViewById(R.id.image_user_edit)
        private val iconDelete: ImageView = view.findViewById(R.id.image_user_delete)

        fun setData(user: User) {
            user.apply {
                txtId.text = user.id.toString()
                txtName.text = fullname
                txtCountry.text = country
                txtAge.text = "$age años"
            }

            iconEdit.setOnClickListener {
                listeners.onEditClicked(user)
            }

            iconDelete.setOnClickListener {
                listeners.onDeleteClicked(user)
            }
        }
    }

    interface UserClickListeners {
        fun onEditClicked(user: User)
        fun onDeleteClicked(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return ViewHolder(view, listeners)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}