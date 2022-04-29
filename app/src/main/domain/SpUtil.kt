import android.content.Context
import android.util.Log

class SpUtil {
    companion object {
        private var mInstance: SpUtil? = SpUtil()
        val instance: SpUtil
            get() {
                if(mInstance == null) {
                    mInstance = SpUtil()
                }
                return mInstance!!
            }
        fun destroy(){
            mInstance = null
        }
    }
    var stopID : Int = -1

    fun saveTableId(ctx: Context, _stopID: Int): Boolean {
        stopID = _stopID
        var sp = ctx.getSharedPreferences("ss", Context.MODE_PRIVATE)
        sp.edit().putInt("stopID", _stopID).commit()
//        Log.d("adam123","saveTableId:"+_stopID)
        return true
    }

    fun getTableId(ctx: Context): Int? {
        if(stopID != -1) return stopID
        var sp = ctx.getSharedPreferences("ss", Context.MODE_PRIVATE)
        val stopID = sp.getInt("stopID", -1)
        return if (stopID == -1) null else stopID
    }
}