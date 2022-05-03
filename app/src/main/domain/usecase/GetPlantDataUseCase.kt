package usecase

import android.util.Log
import api.AnimaData
import api.PlantData
import api.PlantDataService
import api.RetrofitManager
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

                response.body()?.let {
                    PlantData.onNext(it)
                    callback.getPlantDataSussed(it)
                }

            }

            override fun onFailure(call: Call<PlantData?>?, t: Throwable?) {
                callback.getPlantDataFailed("AnimaData更新失敗")

            }
        })
    }

    override fun getDataById(): AnimaData {
        TODO("Not yet implemented")
    }
}