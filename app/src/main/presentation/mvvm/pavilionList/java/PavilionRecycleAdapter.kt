package pavilionList

import pavilionDetailView.PavilionDitelActivity
import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import api.PavilionAreaData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.taipeizoo.R
import extension.listen


//class PavilionlRvData(val data: PavilionAreaData)

class PavilionRvAdapter(var dataList: ArrayList<PavilionAreaData.Result.ResultData>) :
    RecyclerView.Adapter<PavilionRvAdapter.BaseViewHolder<*>>() {


    var mLastClickTime = SystemClock.elapsedRealtime() //Button防連點


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pavilionl_rv_item, parent, false)
        return Header(view).listen { position, type ->

            if (SystemClock.elapsedRealtime() - mLastClickTime < 100) {
                return@listen
            }
            mLastClickTime = SystemClock.elapsedRealtime()

            Toast.makeText(view.context, ""+position, Toast.LENGTH_SHORT).show()

            parent.context?.startActivity(getIntent(view, position))
        }

    }



    fun getIntent(view: View, position: Int): Intent {
        val intent = Intent(view.context, PavilionDitelActivity::class.java)

        intent.putExtra("PavilionID", dataList[position]._id)
//
//        intent.putExtra("TableID", case.tableID)
//        intent.putExtra("TableType", TableType.Baccarat.value.toInt())
        return intent
    }

    override fun getItemCount(): Int = dataList.size



    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (position > 1) {
//            holder.itemView.fadInAnimationHallRv()
        }

        holder.bind(dataList[position] , position)

//        when (holder) {
//
//            is Header -> holder.bind(element as XbbNewHallＴitleModel, position)
//
//            else -> throw IllegalArgumentException()
//        }
    }






    inner class Header(var view: View) : BaseViewHolder<PavilionAreaData.Result.ResultData>(view) {
        val pavilionIV = view.findViewById<ImageView>(R.id.pavilionIV)
        val pavilionName = view.findViewById<TextView>(R.id.pavilionName)
        val pavilionDescribe = view.findViewById<TextView>(R.id.pavilionDescribe)
        val pavilionTime = view.findViewById<TextView>(R.id.pavilionTime)
        val arrowIV = view.findViewById<ImageView>(R.id.arrowIV)
        override fun bind(_object: PavilionAreaData.Result.ResultData, position: Int) {
            pavilionName.text = _object.E_Name
            pavilionDescribe.text = _object.E_Info
            if(_object.E_Memo.equals("")){
                pavilionTime.text = "無休館資訊"
            }else{
                pavilionTime.text = _object.E_Memo
            }

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)


            Glide.with(view.context).load(_object.E_Pic_URL).apply(options).into(pavilionIV)
        }
    }


    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: PavilionAreaData.Result.ResultData, position: Int)
    }
}

