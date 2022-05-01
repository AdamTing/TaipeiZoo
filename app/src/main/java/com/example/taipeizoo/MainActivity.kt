package com.example.taipeizoo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import api.AnimaData
import api.PavilionAreaData
import com.example.taipeizoo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import usecase.GetAnimalDataUseCase
import usecase.iGetAnimaDataUseCaseCallBack
import usecase.iGetEstDataUseCaseCallBack

class MainActivity : AppCompatActivity(), iGetEstDataUseCaseCallBack, iGetAnimaDataUseCaseCallBack {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        var scope : CoroutineScope? = null
        scope = CoroutineScope(Dispatchers.IO)
        scope?.launch {
//            GetPavilionAreaDataUseCase().updateData(this@MainActivity)
            GetAnimalDataUseCase().updateData(this@MainActivity)
        }?:run{
            Log.d("updateBalance","no response")
        }





    }

    override fun getPavilionAreaDataFailed(msg: String) {
//        TODO("Not yet implemented")
    }

    override fun getPavilionAreaDataSussed(data: PavilionAreaData) {
//        TODO("Not yet implemented")
    }

    override fun getAnimaDataFailed(msg: String) {
//        TODO("Not yet implemented")
    }

    override fun getAnimaDataSussed(data: AnimaData) {
//        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}