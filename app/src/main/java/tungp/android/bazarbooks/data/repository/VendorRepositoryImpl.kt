package tungp.android.bazarbooks.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tungp.android.bazarbooks.data.local.database.BazarBooksDatabase
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.data.remote.network.service.ApiService
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.domain.repository.VendorRepository
import javax.inject.Inject

class VendorRepositoryImpl @Inject constructor(
    private val database: BazarBooksDatabase,
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : VendorRepository {
    override fun getVendors(): Flow<BazaResult<List<Vendor>>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.getVendors()
            if (response.statusCode == 200 && response.data != null) {
                emit(BazaResult.Success(response.data.vendors))
            } else {
                emit(
                    BazaResult.Error(
                        Exception(
                            response.statusMessage
                        )
                    )
                )
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)
}
