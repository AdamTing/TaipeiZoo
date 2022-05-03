package api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface iGetPavilionAreaApiCallBack {
    fun getPavilionAreaDataFailed(msg: String)
    fun getPavilionAreaDataSussed(data: PavilionAreaData)
}

class GetPavilionAreaAPI {
    val pavilionAreaDataService: PavilionAreaDataService = RetrofitManager.getInstance().pavilionAreaDataService
    fun updateData(callback:iGetPavilionAreaApiCallBack) {
        val _call: Call<PavilionAreaData> = pavilionAreaDataService.getData()
        // 4. 執行call
        _call.enqueue(object : Callback<PavilionAreaData?> {
            override fun onResponse(call: Call<PavilionAreaData?>?, response: Response<PavilionAreaData?>) {
                response.body()?.let {
                    callback.getPavilionAreaDataSussed(it)
                }?: run {
                    callback.getPavilionAreaDataFailed("PavilionAreaData更新失敗")
                }
            }
            override fun onFailure(call: Call<PavilionAreaData?>?, t: Throwable?) {
                callback.getPavilionAreaDataFailed("PavilionAreaData更新失敗")
            }
        })
    }
}