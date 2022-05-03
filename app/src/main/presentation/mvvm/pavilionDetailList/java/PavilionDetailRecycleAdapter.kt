package pavilionDetailList
import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import api.PavilionAreaData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.taipeizoo.R
import extension.listen

import android.net.Uri
import animalDetail.AnimalDetailActivity
import api.AnimaData


class PavilionDetailRvData(val dataModel: Any?,var type:Int)
class PavilionDetailRvDataImageModel(val url: String?)
class PavilionDetailRvDataDescripModel(val areaData: PavilionAreaData.Result.ResultData)
class PavilionDetailRvDataSubtitleModel(val subtitle: String)
class PavilionDetailRvDataAnimalModel(val animalData: AnimaData.Result.ResultData)
//val dataModel: Any?

class PavilionDetailRecycleAdapter(var dataList: ArrayList<PavilionDetailRvData>) :
    RecyclerView.Adapter<PavilionDetailRecycleAdapter.BaseViewHolder<*>>() {

    var mLastClickTime = SystemClock.elapsedRealtime() //Button防連點
    enum class ItemType(var value: Int) {
        PavilionImage(0),
        PavilionDescribe(1),
        Subtitle(2),
        OtherItem(3);
        companion object {
            private val values = PavilionDetailRecycleAdapter.ItemType.values();
            fun getByValue(value: Int) = values.firstOrNull { it.value == value }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            ItemType.PavilionImage.value ->{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pavilionl_detail_rv_image, parent, false)
                PavilionImageHolder(view)
            }

            ItemType.PavilionDescribe.value ->{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pavilionl_detail_rv_describe, parent, false)
                PavilionDescribeHolder(view)
            }

            ItemType.Subtitle.value ->{
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pavilionl_detail_rv_subtitle, parent, false)
                SubtitleHolder(view)
            }

            ItemType.OtherItem.value -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.pavilionl_detail_rv_item, parent, false)
                OtherItemHolder(view).listen { position, type ->

                    if (SystemClock.elapsedRealtime() - mLastClickTime < 100) {
                        return@listen
                    }
                    mLastClickTime = SystemClock.elapsedRealtime()
                    parent.context?.startActivity(getIntent(view, position))
                }
            }

            else -> throw IllegalArgumentException()
        }


//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.pavilionl_rv_item, parent, false)
//        return Header(view).listen { position, type ->
//
//            if (SystemClock.elapsedRealtime() - mLastClickTime < 100) {
//                return@listen
//            }
//            mLastClickTime = SystemClock.elapsedRealtime()
//
//            Toast.makeText(view.context, ""+position, Toast.LENGTH_SHORT).show()
//
//            parent.context?.startActivity(getIntent(view, position))
//        }

    }



    fun getIntent(view: View, position: Int): Intent {
        val intent = Intent(view.context, AnimalDetailActivity::class.java)
        intent.putExtra("AnimalID", (dataList[position].dataModel as PavilionDetailRvDataAnimalModel).animalData._id)
        return intent
    }

    override fun getItemCount(): Int = dataList.size



    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (position > 1) {
//            holder.itemView.fadInAnimationHallRv()
        }

        val element = dataList[position].dataModel

        when (holder) {

            is PavilionImageHolder -> holder.bind(element as PavilionDetailRvDataImageModel, position)
            is PavilionDescribeHolder -> holder.bind(element as PavilionDetailRvDataDescripModel, position)
            is SubtitleHolder -> holder.bind(element as PavilionDetailRvDataSubtitleModel, position)
            is OtherItemHolder -> holder.bind(element as PavilionDetailRvDataAnimalModel, position)

            else -> throw IllegalArgumentException()
        }
    }



    inner class PavilionImageHolder(var view: View) : BaseViewHolder<PavilionDetailRvDataImageModel>(view) {
        val pavilionIV = view.findViewById<ImageView>(R.id.pavilionIV)
        override fun bind(_object: PavilionDetailRvDataImageModel, position: Int) {
            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
            Glide.with(view.context).load(_object.url).apply(options).into(pavilionIV)
        }
    }

    inner class PavilionDescribeHolder(var view: View) : BaseViewHolder<PavilionDetailRvDataDescripModel>(view) {
        val pavilionDescribe = view.findViewById<TextView>(R.id.pavilionDescribe)
        val pavilionTime = view.findViewById<TextView>(R.id.pavilionTime)
        val pavilionArea = view.findViewById<TextView>(R.id.pavilionArea)
        val openWebPage = view.findViewById<TextView>(R.id.openWebPage)
        override fun bind(_object: PavilionDetailRvDataDescripModel, position: Int) {
            pavilionDescribe.text = _object.areaData.E_Info
            pavilionArea.text = _object.areaData.E_Category
            if(_object.areaData.E_Memo.equals("")){
                pavilionTime.text = "無休館資訊"
            }else{
                pavilionTime.text = _object.areaData.E_Memo
            }

            openWebPage.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse(_object.areaData.E_URL)
                view.context.startActivity(intent)
            }
        }
    }

    inner class SubtitleHolder(var view: View) : BaseViewHolder<PavilionDetailRvDataSubtitleModel>(view) {
        val subtitleTV = view.findViewById<TextView>(R.id.subtitle)
        override fun bind(_object: PavilionDetailRvDataSubtitleModel, position: Int) {
            subtitleTV.text = _object.subtitle
        }
    }

    inner class OtherItemHolder(var view: View) : BaseViewHolder<PavilionDetailRvDataAnimalModel>(view) {
        val pavilionIV = view.findViewById<ImageView>(R.id.pavilionIV)
        val pavilionName = view.findViewById<TextView>(R.id.pavilionName)
        val pavilionDescribe = view.findViewById<TextView>(R.id.pavilionDescribe)
        override fun bind(_object: PavilionDetailRvDataAnimalModel, position: Int) {
            pavilionName.text = _object.animalData.A_Name_En
            pavilionDescribe.text = _object.animalData.A_Distribution

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
            Glide.with(view.context).load(_object.animalData.A_Pic01_URL).apply(options).into(pavilionIV)
        }
    }


    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T, position: Int)
    }
}

