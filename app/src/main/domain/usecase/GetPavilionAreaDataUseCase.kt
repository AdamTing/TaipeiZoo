package usecase

import api.*
import io.reactivex.subjects.BehaviorSubject

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
    override val pavilionAreaData: BehaviorSubject<PavilionAreaData> = BehaviorSubject.create()

    override fun updateData(callback:iGetEstDataUseCaseCallBack) {

        GetPavilionAreaAPI().updateData(object : iGetPavilionAreaApiCallBack {
            override fun getPavilionAreaDataFailed(msg: String) {
                callback.getPavilionAreaDataFailed(msg)
            }
            override fun getPavilionAreaDataSussed(data: PavilionAreaData) {
                callback.getPavilionAreaDataSussed(data)
                pavilionAreaData.onNext(data)
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