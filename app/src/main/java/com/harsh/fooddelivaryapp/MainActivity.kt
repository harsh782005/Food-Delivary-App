package com.harsh.fooddelivaryapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.harsh.fooddelivaryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var navController: NavController? = null
    var appBarConfiguration : AppBarConfiguration ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        enableEdgeToEdge()
        navController = findNavController(R.id.host)
        appBarConfiguration = navController?.graph?.let {
            AppBarConfiguration(it)
        }
        // setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.bottomNav?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item -> navController?.navigate(R.id.itemFragment)
                R.id.bill -> navController?.navigate(R.id.billFragment)
            }
            return@setOnItemSelectedListener true
        }
        setupActionBarWithNavController(navController!!, appBarConfiguration!!)
        navController?.addOnDestinationChangedListener { navController, destination, arguments ->
            when (destination.id) {
                R.id.itemFragment -> {
                    supportActionBar?.title = "Main Menu"
                    binding?.bottomNav?.menu?.findItem(R.id.item)?.isChecked = true
                }
                R.id.billFragment -> {
                    supportActionBar?.title ="Bill Section"
                    binding?.bottomNav?.menu?.findItem(R.id.bill)?.isChecked = true
                }

            }

        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController!!.popBackStack()
    }
}