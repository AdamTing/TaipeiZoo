package com.example.taipeizoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import api.PavilionAreaData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import usecase.GetPavilionAreaDataUseCase
import usecase.iGetEstDataUseCaseCallBack

class MainActivity : AppCompatActivity(), iGetEstDataUseCaseCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       var scope :CoroutineScope? = null
        scope = CoroutineScope(Dispatchers.IO)
        scope?.launch {
            GetPavilionAreaDataUseCase().getPavilionAreaData(this@MainActivity)
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
}