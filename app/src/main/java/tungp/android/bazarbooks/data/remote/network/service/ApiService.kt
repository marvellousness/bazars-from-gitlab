package tungp.android.bazarbooks.data.remote.network.service

import retrofit2.http.GET
import tungp.android.bazarbooks.data.model.CategoriesResponse
import tungp.android.bazarbooks.data.model.HomeFeedsResponse
import tungp.android.bazarbooks.data.model.base.BaseResponse

interface ApiService {

    @GET("mock/getHomeFeeds")
    suspend fun getHomeFeeds(): BaseResponse<HomeFeedsResponse>
    
    @GET("mock/getCategories")
    suspend fun getCategories(): BaseResponse<CategoriesResponse>
}