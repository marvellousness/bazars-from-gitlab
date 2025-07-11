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
        val originalResponse = chain.proceed(chain.request())
        if (chain.request().url.toUri().toString().contains("mock")) {
            val fileName = "${chain.request().url.encodedPathSegments.last()}.json"
            val response = FileUtils.readJsonFromAsset(context, fileName)

            response?.let { resString ->
                return originalResponse
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(resString)
                    .body(
                        resString
                            .toByteArray()
                            .toResponseBody(
                                "application/json".toMediaTypeOrNull()
                            )
                    )
                    .addHeader("content-type", "application/json")
                    .build()
            }
        }

        return originalResponse
    }
}