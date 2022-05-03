package pavilionList

import DisposeBagManager
import TaipeiZooViewModeBaselInterface
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import usecase.GetPavilionAreaDataUseCase

class PavilionRecycleViewVM :TaipeiZooViewModeBaselInterface {

    override val bag: CompositeDisposable = DisposeBagManager.appGetBag()
    var pavilionRvAdapter: BehaviorSubject<PavilionRvAdapter> = BehaviorSubject.create()


    override fun subscribeRx() {
        GetPavilionAreaDataUseCase.instance.pavilionAreaData
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .subscribeBy(onNext = {
                it.result?.results?.let {
                    this.pavilionRvAdapter.onNext(PavilionRvAdapter(it))
                }
            }).addTo(bag)
    }

}