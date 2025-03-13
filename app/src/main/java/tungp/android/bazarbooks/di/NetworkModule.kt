package tungp.android.bazarbooks.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import tungp.android.bazarbooks.data.remote.network.retrofit.MockInterceptor
import tungp.android.bazarbooks.data.remote.network.retrofit.NetworkConstants.API_URL
import tungp.android.bazarbooks.data.remote.network.retrofit.ResultCallAdapterFactory
import tungp.android.bazarbooks.data.remote.network.service.ApiService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        val json: Json = defaultJson
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(createOkHttpClient(context))
            .addConverterFactory(json.asConverterFactory(MIMETYPE_JSON))
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    private fun createOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .addInterceptor(MockInterceptor(context))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}

internal val MIMETYPE_JSON = "application/json".toMediaType()
internal val defaultJson = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
}
