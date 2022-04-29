package usecase

import android.util.Log
import api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetPavilionAreaDataUseCase : iGetPavilionAreaDataUseCase {
    val TAG = "GetEstDataUseCase"
    val pavilionAreaDataService: PavilionAreaDataService = RetrofitManager.getInstance().pavilionAreaDataService


    override fun getPavilionAreaData(callback:iGetEstDataUseCaseCallBack) {
        val _call: Call<PavilionAreaData> = pavilionAreaDataService.getData()
        // 4. 執行call
        _call.enqueue(object : Callback<PavilionAreaData?> {
            override fun onResponse(call: Call<PavilionAreaData?>?, response: Response<PavilionAreaData?>) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
                val response : PavilionAreaData? = response.body()
                Log.d("response,PavilionAreaData", response.toString())


//                response?.let {
//                        it1 -> EstDatasApiUseCase.instance.setData(it1)
//
////                    UIStateManager.instance.debugMsg.onNext("API2更新成功")
//                    EstDatasApiUseCase.instance.estDatas.value?.let {
//                        it.forEach { estData ->
////                            UIStateManager.instance.debugMsg.onNext("路線："+estData.key+",timeDescription："+estData.value.value?.timeDescription?.text?.zh)
//                        }
//                    }
//
//                }?: run {
//                    Log.d(TAG, "API2更新失敗")
////                    UIStateManager.instance.debugMsg.onNext("API2更新失敗")
//                }

            }

            override fun onFailure(call: Call<PavilionAreaData?>?, t: Throwable?) {
                // 連線失敗
//                                Log.d(TAG,"e...:"+e)
                // connection timed out...let's try again
//                UIStateManager.instance.debugMsg.onNext("API2更新失敗")
                Log.d(TAG, "t"+t)
                Log.d(TAG, "call"+call.toString())
                Log.d(TAG, "PavilionAreaData更新失敗")
//                var _timer = Timer()
//                _timer.schedule(object : TimerTask() {
//                    override fun run() {
//                        Log.d(TAG, "API2更新失敗")
//                        getEstData(stopId)
//                    }
//                }, 1000)

            }
        })
    }
}