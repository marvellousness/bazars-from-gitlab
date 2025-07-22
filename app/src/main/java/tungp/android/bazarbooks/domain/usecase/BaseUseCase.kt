package tungp.android.bazarbooks.domain.usecase

interface BaseUseCase<out T : Any, in Params : Any> {
    suspend operator fun invoke(params: Params): Result<T>
}