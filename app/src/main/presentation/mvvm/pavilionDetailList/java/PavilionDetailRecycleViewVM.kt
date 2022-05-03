package pavilionDetailList
import DisposeBagManager
import TaipeiZooViewModeBaselInterface
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import usecase.GetAnimalDataUseCase
import usecase.GetPavilionAreaDataUseCase

class PavilionDetailRecycleViewVM :TaipeiZooViewModeBaselInterface {

    override val bag: CompositeDisposable = DisposeBagManager.appGetBag()
    var pavilionDetailRvAdapter: BehaviorSubject<PavilionDetailRecycleAdapter> = BehaviorSubject.create()


    override fun subscribeRx() {

    }

    fun setPavilionID(id:Int){
        val result = ArrayList<PavilionDetailRvData>()
        var pavilionName :String? = null
        GetPavilionAreaDataUseCase.instance.getDataById(id)?.let { pavilionData->
            result.add(PavilionDetailRvData(PavilionDetailRvDataImageModel(pavilionData.E_Pic_URL),PavilionDetailRecycleAdapter.ItemType.PavilionImage.value))
            result.add(PavilionDetailRvData(PavilionDetailRvDataDescripModel(pavilionData),PavilionDetailRecycleAdapter.ItemType.PavilionDescribe.value))
            pavilionName = pavilionData.E_Name
        }
        result.add(PavilionDetailRvData(PavilionDetailRvDataSubtitleModel("動物資料"),PavilionDetailRecycleAdapter.ItemType.Subtitle.value))

        pavilionName?.let {
            GetAnimalDataUseCase.instance.getDataByPavilion(it).forEach {
                result.add(PavilionDetailRvData(PavilionDetailRvDataAnimalModel(it), PavilionDetailRecycleAdapter.ItemType.OtherItem.value))
            }
        }
        this.pavilionDetailRvAdapter.onNext(PavilionDetailRecycleAdapter(result))

    }

}