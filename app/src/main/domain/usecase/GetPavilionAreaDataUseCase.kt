package usecase

import api.PavilionAreaData
import api.PavilionAreaDataService
import api.RetrofitManager
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

    val pavilionAreaDataService: PavilionAreaDataService = RetrofitManager.getInstance().pavilionAreaDataService
    override val pavilionAreaData: BehaviorSubject<PavilionAreaData> = BehaviorSubject.create()

    override fun updateData(callback:iGetEstDataUseCaseCallBack) {
        val _call: Call<PavilionAreaData> = pavilionAreaDataService.getData()
        // 4. 執行call
        _call.enqueue(object : Callback<PavilionAreaData?> {
            override fun onResponse(call: Call<PavilionAreaData?>?, response: Response<PavilionAreaData?>) {
                response.body()?.let {
                    pavilionAreaData.onNext(it)
                    callback.getPavilionAreaDataFailed("PavilionAreaData更新失敗")
                }
            }

            override fun onFailure(call: Call<PavilionAreaData?>?, t: Throwable?) {
                callback.getPavilionAreaDataFailed("PavilionAreaData更新失敗")
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