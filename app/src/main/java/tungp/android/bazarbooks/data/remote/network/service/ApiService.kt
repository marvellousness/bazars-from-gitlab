package tungp.android.bazarbooks.data.remote.network.service

import retrofit2.http.GET
import tungp.android.bazarbooks.data.remote.network.model.response.BookResponse
import tungp.android.bazarbooks.data.remote.network.model.response.HomeFeedsResponse

interface ApiService {

    @GET("getHomeFeeds")
    suspend fun getHomeFeeds(): Result<HomeFeedsResponse>

    @GET("getBookDetailsById")
    suspend fun getBookDetailsById(): Result<BookResponse>
}