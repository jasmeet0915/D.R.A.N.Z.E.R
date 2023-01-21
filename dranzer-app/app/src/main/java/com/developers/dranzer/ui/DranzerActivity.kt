package com.developers.dranzer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.developers.dranzer.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_dranzer.*

@AndroidEntryPoint
class DranzerActivity : AppCompatActivity() {

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.navigation_device_state,
                R.id.navigation_health_stats,
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dranzer)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        NavigationUI.setupWithNavController(toolbar as Toolbar, navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration) ||
                return super.onSupportNavigateUp()
    }
}