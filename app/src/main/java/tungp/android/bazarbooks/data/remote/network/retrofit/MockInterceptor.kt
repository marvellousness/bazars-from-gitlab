package tungp.android.bazarbooks.data.remote.network.retrofit

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import tungp.android.bazarbooks.util.FileUtils


class MockInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val fileName = "${chain.request().url.encodedPathSegments.last()}.json"
        val resString = FileUtils.readJsonFromAsset(context, fileName)

        return if (resString != null) {
            Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(200)
                .message("OK")
                .body(
                    resString
                        .toByteArray()
                        .toResponseBody(CONTENT_TYPE.toMediaTypeOrNull())
                )
                .addHeader("content-type", CONTENT_TYPE)
                .build()
        } else {
            // Return a mock 404 response instead of hitting the network
            Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(404)
                .message("Mock response not found")
                .body("".toResponseBody(CONTENT_TYPE.toMediaTypeOrNull()))
                .addHeader("content-type", CONTENT_TYPE)
                .build()
        }
    }

    companion object {
        const val CONTENT_TYPE = "application/json"
    }
}