package usecase

import api.AnimaData
import api.AnimaDataService
import api.RetrofitManager
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

    val animaDataService: AnimaDataService = RetrofitManager.getInstance().getaAimaDataService()
    var retryCount = 0
    override val animalData: BehaviorSubject<AnimaData> = BehaviorSubject.create()

    override fun updateData(callback: iGetAnimaDataUseCaseCallBack) {
        val _call: Call<AnimaData> = animaDataService.getData()
        // 4. 執行call
        _call.enqueue(object : Callback<AnimaData?> {
            override fun onResponse(call: Call<AnimaData?>?, response: Response<AnimaData?>) {
                response.body()?.let {
                    animalData.onNext(it)
                    callback.getAnimaDataSussed(it)
                }

            }

            override fun onFailure(call: Call<AnimaData?>?, t: Throwable?) {
                // 連線失敗
                callback.getAnimaDataFailed("AnimaData更新失敗")

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