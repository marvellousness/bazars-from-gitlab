package tungp.android.bazarbooks.util

import android.content.Context
import java.io.IOException

object FileUtils {

    @JvmStatic
    fun readJsonFromAsset(context: Context, nameFile: String): String? {
        var json: String? = null
        try {
            val inputStream = context.assets.open(nameFile)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }
}