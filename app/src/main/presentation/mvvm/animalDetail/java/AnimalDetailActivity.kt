package animalDetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import api.AnimaData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.taipeizoo.R
import com.example.taipeizoo.databinding.ActivityScrollingBinding
import com.google.android.material.appbar.CollapsingToolbarLayout
import usecase.GetAnimalDataUseCase


class AnimalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var animalID = intent.getIntExtra("AnimalID",-1)
        val animaldata = GetAnimalDataUseCase.instance.getDataById(animalID)


        findViewById<TextView>(R.id.contentTV).text = animaldata?.A_Distribution

        Glide.with(this)
            .load(animaldata?.A_Pic01_URL)
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).background = resource
//                    TODO("Not yet implemented")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
//                    TODO("Not yet implemented")
                }

            })

        binding.toolbarLayout.title = animaldata?.A_Name_En
        binding.nestedScrollView.contentTV.text= animaldata?.let { getContentText(it) }
    }

    fun getContentText(animaldata: AnimaData.Result.ResultData):String{
        var result = ""

        if(animaldata.A_AlsoKnown!="") {
            result = result + "別名\n"
            result = result + animaldata.A_AlsoKnown + "\n"
            result = result + "\n"
        }

        result = result+"生長環境\n"
        result = result+animaldata.A_Distribution+"\n"
        result = result+"\n"

        result = result+"生物分類\n"
        result = result+animaldata.A_Phylum+"，"+animaldata.A_Class+"，"+animaldata.A_Order+"，"+animaldata.A_Family+"\n"
        result = result+"\n"

        result = result+"特徵\n"
        result = result+animaldata.A_Feature+"\n"
        result = result+"\n"

        result = result+"行為\n"
        result = result+animaldata.A_Behavior+"\n"
        result = result+"\n"

        result = result+"飲食習慣\n"
        result = result+animaldata.A_Diet+"\n"
        result = result+"\n"

        return result
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}