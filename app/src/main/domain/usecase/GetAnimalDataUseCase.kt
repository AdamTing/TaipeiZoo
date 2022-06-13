package usecase

import android.util.Log
import api.*
import com.google.gson.Gson
import io.reactivex.subjects.BehaviorSubject
import taipeizoo.MyApplication

//取得動物資料的UseCase
class GetAnimalDataUseCase : iGetAnimalDataUseCase {
    companion object {
        private var mInstance:GetAnimalDataUseCase? = GetAnimalDataUseCase()
        val instance: GetAnimalDataUseCase
            get() {
                if(mInstance == null) {
                    mInstance = GetAnimalDataUseCase()
                }
                return mInstance!!
            }
        fun destory(){
            mInstance = null
        }
    }
    override val animalData: BehaviorSubject<AnimaData> = BehaviorSubject.create()

    override fun updateMockData(callback: iGetAnimaDataUseCaseCallBack) {
        val jsonString = MyApplication.appContext?.let { Utils.getJsonFromAssets(it,"animal_data.json") }
        val data = Gson().fromJson(jsonString, AnimaData::class.java)
        callback.getAnimaDataSussed(data)
        animalData.onNext(data)
    }

    override fun updateData(callback: iGetAnimaDataUseCaseCallBack) {
        GetAnimalAPI().getData(object : iGetAnimalApiCallBack{
            override fun getAnimaDataFailed(msg: String) {
                callback.getAnimaDataFailed(msg)
            }
            override fun getAnimaDataSussed(data: AnimaData) {
                callback.getAnimaDataSussed(data)
                animalData.onNext(data)
            }
        })
    }

    override fun getDataByPavilion(E_Name:String): ArrayList<AnimaData.Result.ResultData> {
        val result = ArrayList<AnimaData.Result.ResultData>()
        animalData.value?.result?.results?.let { results->
            results.forEach {
                if(E_Name.contains(it.A_Location.toString())){
                    result.add(it)
                }
            }
        }
        return result
    }

    override fun getDataById(id: Int): AnimaData.Result.ResultData? {
        animalData.value?.result?.results?.let { results->
            results.forEach {
                if(it._id ==id){ return it }
            }
        }
        return null
    }
}