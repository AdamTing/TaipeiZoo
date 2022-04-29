package api

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

data class HealthReportRequest(
    var next_report: Int,
    var lcd_connected: Boolean,
    var lamp_on: Boolean
)

data class PostHealthReportData(
    var next_report: Int? = null,
    var lcd_connected: Boolean? = null,
    var lamp_on: Boolean? = null,
)

interface PostHealthReportService {
    @Headers(
        "Content-Type: application/json",
        "cache-control: no-cache",
        "CompanyToken: fa8fd18e19ca6b5dca7dee0d10609f27"
    )
    @POST("healthReport.jsp")
    fun postHealthReport(@Query("istopId") istopId: Int,@Body body: HealthReportRequest): Call<PostHealthReportData>
}

//fun addPlayer(@Header("x-domain") x_domain : String, @Body body: AddPlayerRequest): Deferred<Response<AddPlayerResponseData>>