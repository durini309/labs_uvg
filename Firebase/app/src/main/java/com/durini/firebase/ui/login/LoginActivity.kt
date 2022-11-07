package com.durini.firebase.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.durini.firebase.data.repository.auth.AuthRepository
import com.durini.firebase.databinding.ActivityLoginBinding
import com.durini.firebase.ui.data.DataActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.buttonLogin.setOnClickListener {
            val email = binding.inputLoginEmail.editText!!.text.toString()
            val password = binding.inputLoginPassword.editText!!.text.toString()


            lifecycleScope.launch {
                val userId = authRepository.signInWithEmailAndPassword(email, password)
                if (!userId.isNullOrEmpty()) {
                    Toast.makeText(
                        baseContext,
                        "Usuario loggeado exitosamente",
                        Toast.LENGTH_LONG
                    ).show()
                    Intent(baseContext, DataActivity::class.java).apply {
                        putExtras(bundleOf("id" to userId))
                        startActivity(this)
                    }
                } else {
                    Toast.makeText(
                        baseContext,
                        "Usuario o contraseña incorrectos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}