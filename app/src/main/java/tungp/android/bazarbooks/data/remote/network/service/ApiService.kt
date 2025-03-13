package tungp.android.bazarbooks.data.remote.network.service

import retrofit2.http.GET
import tungp.android.bazarbooks.data.model.HomeFeedsResponse
import tungp.android.bazarbooks.data.model.base.BaseResponse

interface ApiService {

    @GET("mock/getHomeFeeds")
    suspend fun getHomeFeeds(): BaseResponse<HomeFeedsResponse>
}