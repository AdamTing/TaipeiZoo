package usecase

import api.AnimaData


interface iGetAnimalDataUseCase {
    fun updateData(callback:iGetAnimaDataUseCaseCallBack)
    fun getData():AnimaData
}
interface iGetAnimaDataUseCaseCallBack {
    fun getAnimaDataFailed(msg: String)
    fun getAnimaDataSussed(data: AnimaData)
}