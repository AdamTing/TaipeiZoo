import io.reactivex.disposables.CompositeDisposable

interface TaieiZooViewBaseInterface {
    val bag: CompositeDisposable
    val viewModel: TaipeiZooViewModeBaselInterface
    fun subscribeRx()
    fun onDestroy() {
        bag.clear()
        viewModel.bag.clear()
    }
}