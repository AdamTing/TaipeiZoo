package usecase

import api.PavilionAreaData

interface iGetPavilionAreaDataUseCase {
    fun getPavilionAreaData(callback:iGetEstDataUseCaseCallBack)
}
interface iGetEstDataUseCaseCallBack {
    fun getPavilionAreaDataFailed(msg: String)
    fun getPavilionAreaDataSussed(data: PavilionAreaData)
}