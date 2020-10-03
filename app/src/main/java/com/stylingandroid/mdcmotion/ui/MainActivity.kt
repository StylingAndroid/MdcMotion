package com.stylingandroid.mdcmotion.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.stylingandroid.mdcmotion.R
import com.stylingandroid.mdcmotion.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment)
            ?.also { navHost ->
                binding.bottomNavigation.setupWithNavController(navHost.navController)
            }
    }
}
