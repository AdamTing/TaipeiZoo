package usecase

import api.AnimaData
import io.reactivex.subjects.BehaviorSubject


interface iGetAnimalDataUseCase {
    fun updateData(callback:iGetAnimaDataUseCaseCallBack)
    fun getDataByPavilion(E_Name:String):ArrayList<AnimaData.Result.ResultData>
    fun getDataById(id:Int):AnimaData.Result.ResultData?
    val animalData: BehaviorSubject<AnimaData>
}
interface iGetAnimaDataUseCaseCallBack {
    fun getAnimaDataFailed(msg: String)
    fun getAnimaDataSussed(data: AnimaData)
}