package com.example.capstoneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.capstoneproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navControllers : NavController
    //private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*app butonu çıkartma
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.favoriteFragment,
                R.id.profileFragment,
                R.id.basketFragment,
            )
        )

 */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navControllers=navHostFragment.navController
        binding.bottomNav.setupWithNavController(navControllers)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navControllers)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.successFragment) {
                binding.bottomNav.visibility = View.GONE
            }   else {
                binding.bottomNav.visibility = View.VISIBLE
            }

            if(destination.id == R.id.homeFragment || destination.id == R.id.favoriteFragment || destination.id == R.id.profileFragment || destination.id == R.id.basketFragment || destination.id == R.id.cardDetailFragment){
                binding.toolbar.visibility = View.GONE
            }else {
                binding.toolbar.visibility = View.VISIBLE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navControllers.navigateUp() || super.onSupportNavigateUp()
    }
}