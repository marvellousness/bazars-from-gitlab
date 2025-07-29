package tungp.android.bazarbooks.domain.repository

import kotlinx.coroutines.flow.Flow
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.User

interface AuthRepository {
    fun signIn(email: String, password: String): Flow<BazaResult<User>>
    fun signUp(email: String, password: String, address: String, phone: String): Flow<BazaResult<User>>
} 