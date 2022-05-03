package api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface iGetAnimalApiCallBack {
    fun getAnimaDataFailed(msg: String)
    fun getAnimaDataSussed(data: AnimaData)
}
class GetAnimalAPI {
    val animaDataService: AnimaDataService = RetrofitManager.getInstance().getaAimaDataService()
    var retryCount = 0
    fun getData(callback: iGetAnimalApiCallBack) {
        val _call: Call<AnimaData> = animaDataService.getData()
        // 4. 執行call
        _call.enqueue(object : Callback<AnimaData?> {
            override fun onResponse(call: Call<AnimaData?>?, response: Response<AnimaData?>) {
                response.body()?.let {
                    callback.getAnimaDataSussed(it)
                }?: run {
                    callback.getAnimaDataFailed("AnimaData更新失敗")
                }
            }

            override fun onFailure(call: Call<AnimaData?>?, t: Throwable?) {
                // 連線失敗
                callback.getAnimaDataFailed("AnimaData更新失敗")

            }
        })
    }
}