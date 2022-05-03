package usecase

import android.util.Log
import api.*
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//取得動物資料的UseCase
class GetPlantDataUseCase : iGetPlantDataUseCase {
    companion object {
        private var mInstance:GetPlantDataUseCase? = GetPlantDataUseCase()
        val instance: GetPlantDataUseCase
            get() {
                if(mInstance == null) {
                    mInstance = GetPlantDataUseCase()
                }
                return mInstance!!
            }
        fun destory(){
            mInstance = null
        }
    }



    val TAG = "GetStopConfigUseCase"
    val plantDataService: PlantDataService = RetrofitManager.getInstance().getaPlantDataService()
    var retryCount = 0
    override val PlantData: BehaviorSubject<PlantData> = BehaviorSubject.create()

    override fun updateData(callback: iGetPlantDataUseCaseCallBack) {
        val _call: Call<PlantData> = plantDataService.getData()
        // 4. 執行call
        _call.enqueue(object : Callback<PlantData?> {
            override fun onResponse(call: Call<PlantData?>?, response: Response<PlantData?>) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
//                val response : PavilionAreaData? = response.body()
                Log.d("response,PlantData", response.body()?.result?.results?.get(2).toString())

                response.body()?.let { PlantData.onNext(it) }
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

            override fun onFailure(call: Call<PlantData?>?, t: Throwable?) {
                // 連線失敗
//                                Log.d(TAG,"e...:"+e)
                // connection timed out...let's try again
//                UIStateManager.instance.debugMsg.onNext("API2更新失敗")
                Log.d(TAG, "t"+t)
                Log.d(TAG, "call"+call.toString())
                Log.d(TAG, "AnimaData更新失敗")
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

    override fun getDataById(): AnimaData {
        TODO("Not yet implemented")
    }


//    suspend fun _getStopConfig(stopId:Int): IstopConfig? {
//        val retrofit = Retrofit.Builder()
//                .baseUrl(API_URL.UrlDomain)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .build()
//        val service = retrofit.create(IstopConfigService::class.java)
//        val result = service.getConfig(stopId).await()
//        val response = result.body()
//        Log.d("_getStopConfig",response.toString())
//        return response
//    }
}