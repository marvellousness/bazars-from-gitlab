package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.User
import tungp.android.bazarbooks.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) : DataStateUseCase<SignUpUseCase.Params, User>() {
    data class Params(val email: String, val password: String, val address: String, val phone: String)
    override suspend fun FlowCollector<BazaResult<User>>.execute(params: Params) {
        repository.signUp(params.email, params.password, params.address, params.phone).collect { result ->
            emit(result)
        }
    }
} 