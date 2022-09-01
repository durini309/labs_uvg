package com.durini.frontendavanzado.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.durini.frontendavanzado.R

class TopAppBarActivity : AppCompatActivity() {
    private lateinit var topAppBar: Toolbar
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_appbar)

        // Configuramos nuestra toolbar
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainer_basicToolbarActivity
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Configuramos el Login y el Home como "top level" destinations
        val appbarConfig = AppBarConfiguration(setOf(R.id.loginFragment, R.id.homeFragment))
        topAppBar = findViewById(R.id.toolbar_basicToolbarActivity)
        topAppBar.setupWithNavController(navController, appbarConfig)

        setListeners()
        setNavigationChange()
    }

    private fun setListeners() {
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_favorite -> {
                    Toast.makeText(
                        this,
                        getString(R.string.favs),
                        Toast.LENGTH_LONG
                    ).show()
                    true
                }

                R.id.menu_item_search -> {
                    navController.navigate(R.id.detailsFragment)
                    true
                }

                R.id.menu_item_sync -> {
                    Toast.makeText(
                        this,
                        getString(R.string.sync),
                        Toast.LENGTH_LONG
                    ).show()
                    true
                }

                else -> false
            }
        }
    }

    private fun setNavigationChange() {
        // Agregamos listener que se activará cuando se cambie de destination
        navController.addOnDestinationChangedListener {_, destination, _ ->
            // Ocultaremos Toolbar cuando está en login, y lo mostraremos en el resto
            when(destination.id) {
                R.id.loginFragment -> {
                    topAppBar.visibility = View.GONE
                }

                else -> {
                    topAppBar.visibility = View.VISIBLE
                }
            }
        }
    }
}