import io.reactivex.disposables.CompositeDisposable

object DisposeBagManager{
    private var AppStateBagList :ArrayList<CompositeDisposable> = ArrayList()
    private var UiStateBagList :ArrayList<CompositeDisposable> = ArrayList()

//    fun gameGetBag():CompositeDisposable{
//        val bag = CompositeDisposable()
//        UiStateBagList.add(bag)
//        return bag
//    }

    fun appGetBag():CompositeDisposable{
        val bag = CompositeDisposable()
        AppStateBagList.add(bag)
        return bag
    }

    fun clearAppBag(){
        appBagClear()
    }

    fun clearGameBag(){
        gameBagClear()
    }

    fun gameBagClear(){
        for (bag in UiStateBagList){
            bag.clear()
            bag.dispose()
        }
        UiStateBagList = ArrayList()
    }

    fun appBagClear(){
        for (bag in AppStateBagList){
            bag.clear()
            bag.dispose()
        }
        AppStateBagList = ArrayList()
    }

}