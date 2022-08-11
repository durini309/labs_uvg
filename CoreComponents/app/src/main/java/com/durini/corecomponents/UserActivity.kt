package com.durini.corecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.durini.corecomponents.data.User

class UserActivity : AppCompatActivity() {

    private lateinit var textUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        textUser = findViewById(R.id.text_userActivity_name)

        // Cuando obtienen un objeto de tipo serializable, deben castearlo antes.
        // Deben asegurarse que el name "EXTRA_USER" sea el mismo que el que usaron en el acitvity previa
        val user: User = intent.getSerializableExtra("EXTRA_USER") as User
        textUser.text = "${user.lastname} ${user.name}"
    }
}