package pavilionList

import DisposeBagManager
import TaieiZooViewBaseInterface
import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
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

    }
    public fun init(lifecycleOwner: LifecycleOwner){
        val nameObserver = Observer<PavilionRvAdapter> { pavilionRvAdapter ->

            setRvAdapter(pavilionRvAdapter)
        }

        viewModel.pavilionRvAdapter
            .observe(lifecycleOwner, nameObserver)
    }




    fun setRvAdapter(pavilionRvAdapter: PavilionRvAdapter) {
        this.adapter = pavilionRvAdapter
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        this.layoutManager = mLayoutManager
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

    }






}