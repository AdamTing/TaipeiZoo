import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class MyUtil {
    companion object {
        private var mInstance: MyUtil? = MyUtil()
        val instance: MyUtil
            get() {
                if(mInstance == null) {
                    mInstance = MyUtil()
                }
                return mInstance!!
            }
        fun destroy(){
            mInstance = null
        }
    }
    var stopID : Int = -1


    fun getHrMin(): Int {
//        val dff = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dff = SimpleDateFormat("HH:mm:ss")
        dff.setTimeZone(TimeZone.getTimeZone("GMT+8"))
        val ee1: String = dff.format(Date())
        Log.d("getHrMin","ee1:"+ee1)

        var result = 0
        ee1.split(":").forEachIndexed { index, s ->
            when (index){
                0 -> {
                    result += (s.toInt()) * 60 * 60
                }
                1 -> {
                    result += s.toInt() * 60
                }
                2 -> {
                    result += s.toInt()
                }
            }
        }
        Log.d("getHrMin","result:"+result)
        return result
    }

    fun getTimeForDebug(): String {
        val dff = SimpleDateFormat("HH:mm")
        dff.setTimeZone(TimeZone.getTimeZone("GMT+8"))
        val ee1: String = dff.format(Date())
        return ee1
    }
}