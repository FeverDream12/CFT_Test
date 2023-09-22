package com.example.cft_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.cft_test.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MAIN = this

        val path = applicationContext.filesDir
        val letDirectory = File(path, "LET")
        letDirectory.mkdirs()

        USERNAMEFILE = File(letDirectory, "username.txt")

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        if(USERNAMEFILE.exists()){
            USERNAME =  FileInputStream(USERNAMEFILE).bufferedReader().use { it.readText() }
            navController.navigate(R.id.action_signInFragment_to_homeFragment)
        }
    }
}