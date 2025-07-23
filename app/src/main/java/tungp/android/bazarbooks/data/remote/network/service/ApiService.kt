package tungp.android.bazarbooks.data.remote.network.service

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import tungp.android.bazarbooks.data.remote.model.request.AddToCartRequest
import tungp.android.bazarbooks.data.remote.model.response.AddToCartResponse
import tungp.android.bazarbooks.data.remote.model.response.BookDetailResponse
import tungp.android.bazarbooks.data.remote.model.response.CategoriesResponse
import tungp.android.bazarbooks.data.remote.model.response.HomeFeedsResponse
import tungp.android.bazarbooks.data.remote.model.response.VendorsResponse
import tungp.android.bazarbooks.data.remote.model.response.AuthorsResponse
import tungp.android.bazarbooks.data.remote.model.response.CartResponse
import tungp.android.bazarbooks.data.remote.model.response.ClearCartResponse
import tungp.android.bazarbooks.data.remote.model.response.ConfirmOrderResponse
import tungp.android.bazarbooks.data.remote.model.response.RemoveCartResponse
import tungp.android.bazarbooks.data.remote.model.request.RemoveFromCartRequest
import tungp.android.bazarbooks.data.remote.model.response.UpdateCartResponse
import tungp.android.bazarbooks.data.remote.model.base.BaseResponse

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
    //suspend fun getBookDetails(@Path("bookId") bookId: String): BaseResponse<BookDetailResponse>

    @GET("mock/getBookDetails_{bookId}")
    suspend fun getBookDetails(bookId: String): BaseResponse<BookDetailResponse>
    
    @POST("mock/addToCart")
    suspend fun addToCart(@Body request: AddToCartRequest): BaseResponse<AddToCartResponse>

    @POST("mock/getCart")
    suspend fun getCart(): BaseResponse<CartResponse>

    suspend fun updateCartItem(
        cartItemId: String,
        quantity: Int
    ): BaseResponse<UpdateCartResponse>

    @POST("mock/removeFromCart")
    suspend fun removeFromCart(@Body request: RemoveFromCartRequest): BaseResponse<RemoveCartResponse>

    suspend fun clearCart(): BaseResponse<ClearCartResponse>

    @POST("mock/confirmOrder")
    suspend fun confirmOrder(): BaseResponse<ConfirmOrderResponse>

}