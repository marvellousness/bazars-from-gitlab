package tungp.android.bazarbooks.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.data.remote.model.request.SignInRequest
import tungp.android.bazarbooks.data.remote.model.request.SignUpRequest
import tungp.android.bazarbooks.data.remote.network.service.ApiService
import tungp.android.bazarbooks.domain.model.User
import tungp.android.bazarbooks.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AuthRepository {

    override fun signIn(email: String, password: String): Flow<BazaResult<User>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.signIn(SignInRequest(email, password))
            val user = response.data?.user?.asDomainModel()
            if (response.statusCode == 200 && user != null) {
                emit(BazaResult.Success(user))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage)))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)

    override fun signUp(
        email: String,
        password: String,
        address: String,
        phone: String,
    ): Flow<BazaResult<User>> = flow {
        emit(BazaResult.Loading)
        try {
            val response = apiService.signUp(SignUpRequest(email, password, address, phone))
            val user = response.data?.user?.asDomainModel()
            if (response.statusCode == 200 && user != null) {
                emit(BazaResult.Success(user))
            } else {
                emit(BazaResult.Error(Exception(response.statusMessage)))
            }
        } catch (e: Exception) {
            emit(BazaResult.Error(e))
        }
    }.flowOn(ioDispatcher)
} 