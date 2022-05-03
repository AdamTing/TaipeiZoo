package api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

data class PavilionAreaData(
    val result: Result? = null,
) {
    data class Result(
        var limit: Int? = null,
        var offset: Int? = null,
        var count: Int? = null,
        var sort: String? = null,
        var results: ArrayList<ResultData>? = null,
    ) {
        data class ResultData(
            val _id: Int? = null,
            val E_no: String? = null,
            val E_Category: String? = null,
            val E_Name: String? = null,
            val E_Pic_URL: String? = null,
            val E_Info: String? = null,
            val E_Memo: String? = null,
            val E_Geo: String? = null,
            val E_URL: String? = null
        )
    }
}

interface PavilionAreaDataService {
    @Headers(

        "cache-control: no-cache",
    )
    @GET("api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire&offset=0&limit=1000")
    fun getData(): Call<PavilionAreaData>
}

