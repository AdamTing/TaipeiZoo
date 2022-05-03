package api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface iGetPlantDataApiCallBack {
    fun getPlantDataFailed(msg: String)
    fun getPlantDataSussed(data: PlantData)
}

class GetPlantAPI {
    val plantDataService: PlantDataService = RetrofitManager.getInstance().getaPlantDataService()
    var retryCount = 0

    fun updateData(callback: iGetPlantDataApiCallBack) {
        val _call: Call<PlantData> = plantDataService.getData()
        _call.enqueue(object : Callback<PlantData?> {
            override fun onResponse(call: Call<PlantData?>?, response: Response<PlantData?>) {
                response.body()?.let {
                    callback.getPlantDataSussed(it)
                }?: run {
                    callback.getPlantDataFailed("plantData")
                }
            }

            override fun onFailure(call: Call<PlantData?>?, t: Throwable?) {
                callback.getPlantDataFailed("plantData")

            }
        })
    }
}