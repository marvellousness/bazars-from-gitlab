package tungp.android.bazarbooks.domain.usecase

import kotlinx.coroutines.flow.FlowCollector
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.User
import tungp.android.bazarbooks.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) : DataStateUseCase<SignInUseCase.Params, User>() {
    data class Params(val email: String, val password: String)
    override suspend fun FlowCollector<BazaResult<User>>.execute(params: Params) {
        repository.signIn(params.email, params.password).collect { result ->
            emit(result)
        }
    }
} 