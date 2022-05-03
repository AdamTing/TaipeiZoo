package api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

data class PlantData(
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
            val _id:Int?=null,
            val F_Name_Ch:String?=null,
            val F_Summary:String?=null,
            val F_Keywords:String?=null,
            val F_AlsoKnown:String?=null,
            val F_Geo:String?=null,
            val F_Location:String?=null,
            val F_Name_En:String?=null,
            val F_Name_Latin:String?=null,
            val F_Family:String?=null,
            val F_Genus:String?=null,
            val F_Brief:String?=null,
            val F_Feature:String?=null,
            val F_FunctionApplication:String?=null,
            val F_Code:String?=null,
            val F_Pic01_ALT:String?=null,
            val F_Pic01_URL:String?=null,
            val F_Pic02_ALT:String?=null,
            val F_Pic02_URL:String?=null,
            val F_Pic03_ALT:String?=null,
            val F_Pic03_URL:String?=null,
            val F_Pic04_ALT:String?=null,
            val F_Pic04_URL:String?=null,
            val F_pdf01_ALT:String?=null,
            val F_pdf01_URL:String?=null,
            val F_pdf02_ALT:String?=null,
            val F_pdf02_URL:String?=null,
            val F_Voice01_ALT:String?=null,
            val F_Voice01_URL:String?=null,
            val F_Voice02_ALT:String?=null,
            val F_Voice02_URL:String?=null,
            val F_Voice03_ALT:String?=null,
            val F_Voice03_URL:String?=null,
            val F_Vedio_URL:String?=null,
            val F_Update:String?=null,
            val F_CID:String?=null
        )
    }
}


interface PlantDataService {
    @Headers(
        "Content-Type: application/json",
        "cache-control: no-cache",
    )
    @GET("api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire&offset=0&limit=1000")
    fun getData(): Call<PlantData>
}

