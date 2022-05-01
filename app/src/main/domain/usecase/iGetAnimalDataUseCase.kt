package usecase

import api.AnimaData


interface iGetAnimalDataUseCase {
    fun getAnimalData(callback:iGetAnimaDataUseCaseCallBack)
}
interface iGetAnimaDataUseCaseCallBack {
    fun getAnimaDataFailed(msg: String)
    fun getAnimaDataSussed(data: AnimaData)
}