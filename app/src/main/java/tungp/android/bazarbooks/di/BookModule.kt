package tungp.android.bazarbooks.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import tungp.android.bazarbooks.domain.usecase.GetCategoriesUseCase
import tungp.android.bazarbooks.domain.usecase.GetHomeFeedsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object BookModule {

    @Provides
    fun providesGetHomeFeedsUseCase(repository: RemoteRepository): GetHomeFeedsUseCase {
        return GetHomeFeedsUseCase(repository = repository)
    }
    
    @Provides
    fun providesGetCategoriesUseCase(repository: RemoteRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository = repository)
    }
}