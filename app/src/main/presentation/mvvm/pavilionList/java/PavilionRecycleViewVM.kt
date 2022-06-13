package pavilionList

import DisposeBagManager
import TaipeiZooViewModeBaselInterface
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import usecase.GetPavilionAreaDataUseCase

class PavilionRecycleViewVM :TaipeiZooViewModeBaselInterface {

    override val bag: CompositeDisposable = DisposeBagManager.appGetBag()
//    var pavilionRvAdapter: BehaviorSubject<PavilionRvAdapter> = BehaviorSubject.create()
    val pavilionRvAdapter: MutableLiveData<PavilionRvAdapter> by lazy {
        MutableLiveData<PavilionRvAdapter>()
    }

    override fun subscribeRx() {
        GetPavilionAreaDataUseCase.instance.pavilionAreaData
            .distinctUntilChanged()
            .subscribeOn(mainThread())
            .subscribeBy(onNext = {
                it.result?.results?.let {
                    pavilionRvAdapter.value = PavilionRvAdapter(it)
                }
            }).addTo(bag)
    }

}