
import android.content.Context
import java.io.IOException

import java.io.InputStream
import java.nio.charset.Charset


object Utils {
    fun getJsonFromAssets(context: Context, fileName: String): String? {
        val jsonString: String
        jsonString = try {
            val inputStream = context.getAssets().open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val charset: Charset = Charsets.UTF_8
            String(buffer, charset)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}