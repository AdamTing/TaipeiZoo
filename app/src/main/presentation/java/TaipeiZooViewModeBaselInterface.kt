import io.reactivex.disposables.CompositeDisposable

interface TaipeiZooViewModeBaselInterface {
    val bag: CompositeDisposable
    fun onDestroy() { this.bag.dispose() }
    fun subscribeRx()
}
