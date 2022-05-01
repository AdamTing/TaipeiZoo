package usecase

import api.AnimaData
import api.PavilionAreaData

interface iGetPavilionAreaDataUseCase {
    fun updateData(callback:iGetEstDataUseCaseCallBack)
    fun getData(): PavilionAreaData
}
interface iGetEstDataUseCaseCallBack {
    fun getPavilionAreaDataFailed(msg: String)
    fun getPavilionAreaDataSussed(data: PavilionAreaData)
}
