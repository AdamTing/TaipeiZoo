package usecase

import android.util.Log
import api.*
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//取得動物資料的UseCase
class GetAnimalDataUseCase : iGetAnimalDataUseCase {
    companion object {
        private var mInstance:GetAnimalDataUseCase? = GetAnimalDataUseCase()
        val instance: GetAnimalDataUseCase
            get() {
                if(mInstance == null) {
                    mInstance = GetAnimalDataUseCase()
                }
                return mInstance!!
            }
        fun destory(){
            mInstance = null
        }
    }



    val TAG = "GetStopConfigUseCase"
    val animaDataService: AnimaDataService = RetrofitManager.getInstance().getaAimaDataService()
    var retryCount = 0
    override val animalData: BehaviorSubject<AnimaData> = BehaviorSubject.create()

    override fun updateData(callback: iGetAnimaDataUseCaseCallBack) {
        val _call: Call<AnimaData> = animaDataService.getData()
        // 4. 執行call
        _call.enqueue(object : Callback<AnimaData?> {
            override fun onResponse(call: Call<AnimaData?>?, response: Response<AnimaData?>) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
//                val response : PavilionAreaData? = response.body()
                Log.d("response,AnimaData", response.body()?.result?.results?.size.toString())

                response.body()?.let { animalData.onNext(it) }
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

            override fun onFailure(call: Call<AnimaData?>?, t: Throwable?) {
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

    override fun getDataByPavilion(E_Name:String): ArrayList<AnimaData.Result.ResultData> {
        val result = ArrayList<AnimaData.Result.ResultData>()
        animalData.value?.result?.results?.let { results->
            results.forEach {
                if(E_Name.contains(it.A_Location.toString())){
                    result.add(it)
                }
            }
        }
        return result
    }

    override fun getDataById(id: Int): AnimaData.Result.ResultData? {
        animalData.value?.result?.results?.let { results->
            results.forEach {
                if(it._id ==id){ return it }
            }
        }
        return null
    }
}