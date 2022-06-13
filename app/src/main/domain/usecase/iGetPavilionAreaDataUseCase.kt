package usecase

import api.AnimaData
import api.PavilionAreaData
import io.reactivex.subjects.BehaviorSubject

interface iGetPavilionAreaDataUseCase {
    fun updateData(callback:iGetEstDataUseCaseCallBack)
    fun updateMockData(callback:iGetEstDataUseCaseCallBack)
    fun getDataById(id:Int): PavilionAreaData.Result.ResultData?
    val pavilionAreaData: BehaviorSubject<PavilionAreaData>
}
interface iGetEstDataUseCaseCallBack {
    fun getPavilionAreaDataFailed(msg: String)
    fun getPavilionAreaDataSussed(data: PavilionAreaData)
}
