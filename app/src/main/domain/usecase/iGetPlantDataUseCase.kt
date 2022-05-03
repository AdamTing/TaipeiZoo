package usecase

import api.AnimaData
import api.PlantData
import io.reactivex.subjects.BehaviorSubject


interface iGetPlantDataUseCase {
    fun updateData(callback:iGetPlantDataUseCaseCallBack)
    fun getDataById():AnimaData
    val PlantData: BehaviorSubject<PlantData>
}
interface iGetPlantDataUseCaseCallBack {
    fun getAnimaDataFailed(msg: String)
    fun getAnimaDataSussed(data: AnimaData)
}