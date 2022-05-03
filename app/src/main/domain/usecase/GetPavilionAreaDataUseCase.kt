package usecase

import android.util.Log
import api.*
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//取得展館資料的UseCase
class GetPavilionAreaDataUseCase : iGetPavilionAreaDataUseCase {
    companion object {
        private var mInstance:GetPavilionAreaDataUseCase? = GetPavilionAreaDataUseCase()
        val instance: GetPavilionAreaDataUseCase
            get() {
                if(mInstance == null) {
                    mInstance = GetPavilionAreaDataUseCase()
                }
                return mInstance!!
            }
        fun destory(){
            mInstance = null
        }
    }

    val TAG = "GetEstDataUseCase"
    val pavilionAreaDataService: PavilionAreaDataService = RetrofitManager.getInstance().pavilionAreaDataService
    override val pavilionAreaData: BehaviorSubject<PavilionAreaData> = BehaviorSubject.create()

    override fun updateData(callback:iGetEstDataUseCaseCallBack) {
        val _call: Call<PavilionAreaData> = pavilionAreaDataService.getData()
        // 4. 執行call
        _call.enqueue(object : Callback<PavilionAreaData?> {
            override fun onResponse(call: Call<PavilionAreaData?>?, response: Response<PavilionAreaData?>) {
                // 連線成功
                // 回傳的資料已轉成Albums物件，可直接用get方法取得特定欄位
                response.body()?.let { pavilionAreaData.onNext(it) }
                Log.d("response,PavilionAreaData", response.toString())

            }

            override fun onFailure(call: Call<PavilionAreaData?>?, t: Throwable?) {
                Log.d(TAG, "t"+t)
                Log.d(TAG, "call"+call.toString())
                Log.d(TAG, "PavilionAreaData更新失敗")


            }
        })
    }

    override fun getDataById(id:Int): PavilionAreaData.Result.ResultData? {
        pavilionAreaData.value?.result?.results?.let { results->
            results.forEach {
                if(it._id==id){ return  it }
            }
        }
        return null
    }
}