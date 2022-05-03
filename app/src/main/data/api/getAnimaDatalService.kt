package api

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

data class AnimaData(
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
            val A_Name_Ch: String? = null,
            val A_Summary: String? = null,
            val A_Keywords: String? = null,
            val A_AlsoKnown: String? = null,
            val A_Geo: String? = null,
            val A_Location: String? = null,
            val A_POIGroup: String? = null,
            val A_Name_En: String? = null,
            val A_Name_Latin: String? = null,
            val A_Phylum: String? = null,
            val A_Class: String? = null,
            val A_Order: String? = null,
            val A_Family: String? = null,
            val A_Conservation: String? = null,
            val A_Distribution: String? = null,
            val A_Habitat: String? = null,
            val A_Feature: String? = null,
            val A_Behavior: String? = null,
            val A_Diet: String? = null,
            val A_Crisis: String? = null,
            val A_Interpretation: String? = null,
            val A_Theme_Name: String? = null,
            val A_Theme_URL: String? = null,
            val A_Adopt: String? = null,
            val A_Code: String? = null,
            val A_Pic01_ALT: String? = null,
            val A_Pic01_URL: String? = null,
            val A_Pic02_ALT: String? = null,
            val A_Pic02_URL: String? = null,
            val A_Pic03_ALT: String? = null,
            val A_Pic03_URL: String? = null,
            val A_Pic04_ALT: String? = null,
            val A_Pic04_URL: String? = null,
            val A_pdf01_ALT: String? = null,
            val A_pdf01_URL: String? = null,
            val A_pdf02_ALT: String? = null,
            val A_pdf02_URL: String? = null,
            val A_Voice01_ALT: String? = null,
            val A_Voice01_URL: String? = null,
            val A_Voice02_ALT: String? = null,
            val A_Voice02_URL: String? = null,
            val A_Voice03_ALT: String? = null,
            val A_Voice03_URL: String? = null,
            val A_Vedio_URL: String? = null,
            val A_Update: String? = null,
            val A_CID: String? = null
        )
    }
}


interface AnimaDataService {
    @Headers(
        "Content-Type: application/json",
        "cache-control: no-cache",
    )
    @GET("api/v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire&limit=1000")
    fun getData(): Call<AnimaData>
}

