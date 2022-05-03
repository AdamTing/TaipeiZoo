package usecase

import api.AnimaData
import api.GetPlantAPI
import api.PlantData
import api.iGetPlantDataApiCallBack
import io.reactivex.subjects.BehaviorSubject

//取得動物資料的UseCase
class GetPlantDataUseCase : iGetPlantDataUseCase {
    companion object {
        private var mInstance:GetPlantDataUseCase? = GetPlantDataUseCase()
        val instance: GetPlantDataUseCase
            get() {
                if(mInstance == null) {
                    mInstance = GetPlantDataUseCase()
                }
                return mInstance!!
            }
        fun destory(){
            mInstance = null
        }
    }

    var retryCount = 0
    override val PlantData: BehaviorSubject<PlantData> = BehaviorSubject.create()

    override fun updateData(callback: iGetPlantDataUseCaseCallBack) {
        GetPlantAPI().updateData(object :iGetPlantDataApiCallBack{
            override fun getPlantDataFailed(msg: String) {
                callback.getPlantDataFailed(msg)
            }
            override fun getPlantDataSussed(data: PlantData) {
                PlantData.onNext(data)
                callback.getPlantDataSussed(data)
            }
        })
    }

    override fun getDataById(): AnimaData {
        TODO("Not yet implemented")
    }
}