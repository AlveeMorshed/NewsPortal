package com.moinul.newsportal

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.moinul.newsportal.adapters.ViewPagerAdapter
import com.moinul.newsportal.databinding.ActivityMainBinding
import com.moinul.newsportal.fragments.BookmarksFragment
import com.moinul.newsportal.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavBar = binding.bottomNavbar
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        bottomNavBar.setOnItemSelectedListener{
            when(it.itemId){

                R.id.home_bottom_nav -> {
                    findNavController(R.id.nav_host_fragment_container).navigate(R.id.homeFragment)
                }

                R.id.bookmarks_bottom_nav -> {
                    findNavController(R.id.nav_host_fragment_container).navigate(R.id.bookmarksFragment)
                }

                else -> { }
            }
            true
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object{
        fun checkConnectivity(context: Context):Boolean{
            val connectivityManager= context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo=connectivityManager.activeNetworkInfo
            return  networkInfo!=null && networkInfo.isConnected
        }
    }
}