package pavilionList

import DisposeBagManager
import TaieiZooViewBaseInterface
import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class PavilionRecycleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr),TaieiZooViewBaseInterface {


    override val bag: CompositeDisposable = DisposeBagManager.appGetBag()
    override val viewModel: PavilionRecycleViewVM = PavilionRecycleViewVM()

    override fun subscribeRx() {
        TODO("Not yet implemented")
    }

    init {
        viewModel.subscribeRx()

        viewModel.pavilionRvAdapter
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
//                    this.adapter = it
//                    this.adapter!!.notifyDataSetChanged()

                    setRvAdapter(it)
//                    if (this.adapter == null){
//                        this.adapter = it
//                    } else {
//                        (this.adapter as XbbNewHallRvAdapter).updateData(it.second)
//                        this.adapter!!.notifyDataSetChanged()
//                    }
                }
            ).addTo(bag)
    }

    fun setRvAdapter(pavilionRvAdapter: PavilionRvAdapter) {
        this.adapter = pavilionRvAdapter
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        this.layoutManager = mLayoutManager
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

//        UIStateHall.instance.HALL_LIST_PPRESENT_MODE
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy(onNext = {
//                if (it == XbbNewHallRvAdapter.ListMode.Grid) {
//                    val mLayoutManager = GridLayoutManager(context, 3)
//                    mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//                        override fun getSpanSize(position: Int): Int {
//                            when (adapter?.getItemViewType(position)) {
//                                XbbNewHallRvAdapter.ItemType.AD_BANNER_TYPE.value -> return 3
//                                XbbNewHallRvAdapter.ItemType.HEADER_ITEM_TYPE.value -> return 3
//                                else -> return 1
//                            }
//                        }
//                    }
//                    this.layoutManager = mLayoutManager
//                } else {
//                    this.layoutManager = LinearLayoutManager(context)
//                }
//
//                val viewHolderType0 = recycledViewPool.getRecycledView(0)
//                val viewHolderType1 = recycledViewPool.getRecycledView(1)
//
//                recycledViewPool.clear()
//
//                viewHolderType0?.let {
//                    recycledViewPool.putRecycledView(viewHolderType0)
//                }
//
//                viewHolderType1?.let {
//                    recycledViewPool.putRecycledView(viewHolderType1)
//                }
//                this.adapter?.notifyDataSetChanged()
//            }).addTo(bag)
    }






}