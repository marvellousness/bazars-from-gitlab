package tungp.android.bazarbooks.util

import android.content.Context
import com.google.gson.Gson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import okio.buffer
import okio.source
import java.io.IOException

@Serializable
data class Config(
    @SerialName("attributeName1")
    val attributeName1: String,
    @SerialName("attributeName2")
    val attributeName2: String,
) {
    companion object {
        /**
         * Loads and parses configuration from assets file
         * @param context Android context
         * @param fileName Name of the config file in assets
         * @return Parsed Config object
         * @throws ConfigLoadException if loading or parsing fails
         */
        fun fromAssets(context: Context, fileName: String): Config {
            return try {
                val jsonContent = readFileContent(context, fileName)
                parseConfig(jsonContent)
            } catch (e: IOException) {
                throw ConfigLoadException("Failed to load config from assets", e)
            } catch (e: Exception) {
                throw ConfigLoadException("Failed to parse config", e)
            }
        }

        private fun readFileContent(context: Context, fileName: String): String {
            return context.assets.open(fileName).source().buffer().use { 
                it.readUtf8() 
            }
        }

        private fun parseConfig(jsonContent: String): Config {
            return Gson().fromJson(jsonContent, Config::class.java)
                ?: throw ConfigLoadException("Config parsing resulted in null")
        }
    }
}

class ConfigLoadException(message: String, cause: Throwable? = null) : 
    RuntimeException(message, cause)