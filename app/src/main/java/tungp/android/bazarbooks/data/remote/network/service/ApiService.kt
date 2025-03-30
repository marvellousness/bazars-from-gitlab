package tungp.android.bazarbooks.data.remote.network.service

import retrofit2.http.GET
import retrofit2.http.Path
import tungp.android.bazarbooks.data.model.BookDetailResponse
import tungp.android.bazarbooks.data.model.CategoriesResponse
import tungp.android.bazarbooks.data.model.HomeFeedsResponse
import tungp.android.bazarbooks.data.model.VendorsResponse
import tungp.android.bazarbooks.data.model.AuthorsResponse
import tungp.android.bazarbooks.data.model.base.BaseResponse

interface ApiService {

    @GET("mock/getHomeFeeds")
    suspend fun getHomeFeeds(): BaseResponse<HomeFeedsResponse>
    
    @GET("mock/getCategories")
    suspend fun getCategories(): BaseResponse<CategoriesResponse>
    
    @GET("mock/getVendors")
    suspend fun getVendors(): BaseResponse<VendorsResponse>

    @GET("mock/getAuthors")
    suspend fun getAuthors(): BaseResponse<AuthorsResponse>

    //@GET("mock/getBookDetails/{bookId}")
//    suspend fun getBookDetails(@Path("bookId") bookId: String): BaseResponse<BookDetailResponse>

    @GET("mock/getBookDetails_{bookId}")
    suspend fun getBookDetails(bookId: String): BaseResponse<BookDetailResponse>
}