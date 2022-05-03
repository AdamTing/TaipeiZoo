package taipeizoo
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import api.AnimaData
import api.PavilionAreaData
import api.PlantData
import com.example.taipeizoo.R
import com.example.taipeizoo.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import usecase.*

class MainActivity : AppCompatActivity(), iGetEstDataUseCaseCallBack, iGetAnimaDataUseCaseCallBack,
    iGetPlantDataUseCaseCallBack {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Syn Data
        var scope : CoroutineScope? = null
        scope = CoroutineScope(Dispatchers.IO)
        scope?.launch {
            GetPavilionAreaDataUseCase.instance.updateData(this@MainActivity)
            GetAnimalDataUseCase.instance.updateData(this@MainActivity)
            GetPlantDataUseCase.instance.updateData(this@MainActivity)
        }?:run{
            Log.d("updateBalance","no response")
        }



//初始化ProgressDialog
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
    }

    override fun getPavilionAreaDataFailed(msg: String) {
//        TODO("Not yet implemented")
    }

    override fun getPavilionAreaDataSussed(data: PavilionAreaData) {
//        TODO("Not yet implemented")
    }

    override fun getPlantDataFailed(msg: String) {
//        TODO("Not yet implemented")
    }

    override fun getPlantDataSussed(data: PlantData) {
//        TODO("Not yet implemented")

    }

    override fun getAnimaDataFailed(msg: String) {
//        TODO("Not yet implemented")
    }

    override fun getAnimaDataSussed(data: AnimaData) {
        progressDialog.dismiss()
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}