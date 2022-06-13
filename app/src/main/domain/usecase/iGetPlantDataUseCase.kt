package usecase

import api.AnimaData
import api.PlantData
import io.reactivex.subjects.BehaviorSubject


interface iGetPlantDataUseCase {
    fun updateMockData(callback:iGetPlantDataUseCaseCallBack)
    fun updateData(callback:iGetPlantDataUseCaseCallBack)
    fun getDataById():AnimaData
    val PlantData: BehaviorSubject<PlantData>
}
interface iGetPlantDataUseCaseCallBack {
    fun getPlantDataFailed(msg: String)
    fun getPlantDataSussed(data: PlantData)
}