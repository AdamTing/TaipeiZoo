package pavilionDetailView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taipeizoo.R
import pavilionDetailList.PavilionDetailRecycleView
import usecase.GetPavilionAreaDataUseCase

class PavilionDitelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pavilion_ditel)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

        var pavilionID = intent.getIntExtra("PavilionID",-1)
        var resultData= GetPavilionAreaDataUseCase.instance.getDataById(pavilionID)
        setActionBarTitle(""+resultData?.E_Name)

        val pavilionDetailRecycleViewRV = findViewById<PavilionDetailRecycleView>(R.id.pavilionDetailRecycleViewRV)

        pavilionDetailRecycleViewRV.viewModel.setPavilionID(pavilionID)
    }

    fun setActionBarTitle(title:String){
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}