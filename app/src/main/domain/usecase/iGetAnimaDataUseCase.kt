package usecase

import api.AnimaData


interface iGetAnimaDataUseCase {
    fun getStopConfig(callback:iGetAnimaDataUseCaseCallBack)
}
interface iGetAnimaDataUseCaseCallBack {
    fun getAnimaDataFailed(msg: String)
    fun getAnimaDataSussed(data: AnimaData)
}