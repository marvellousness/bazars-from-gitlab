package tungp.android.bazarbooks.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import tungp.android.bazarbooks.domain.usecase.AddToCartUseCase
import tungp.android.bazarbooks.domain.usecase.GetBookDetailUseCase
import tungp.android.bazarbooks.domain.usecase.GetCategoriesUseCase
import tungp.android.bazarbooks.domain.usecase.GetHomeFeedsUseCase
import tungp.android.bazarbooks.domain.usecase.GetVendorsUseCase
import tungp.android.bazarbooks.domain.usecase.GetCartUseCase
import tungp.android.bazarbooks.domain.usecase.UpdateCartItemUseCase
import tungp.android.bazarbooks.domain.usecase.RemoveCartItemUseCase
import tungp.android.bazarbooks.domain.usecase.ClearCartUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    fun providesGetHomeFeedsUseCase(repository: RemoteRepository): GetHomeFeedsUseCase {
        return GetHomeFeedsUseCase(repository = repository)
    }
    
    @Provides
    fun providesGetCategoriesUseCase(repository: RemoteRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository = repository)
    }
    
    @Provides
    fun providesGetVendorsUseCase(repository: RemoteRepository): GetVendorsUseCase {
        return GetVendorsUseCase(repository = repository)
    }
    
    @Provides
    fun providesGetBookDetailUseCase(repository: RemoteRepository): GetBookDetailUseCase {
        return GetBookDetailUseCase(repository = repository)
    }
    
    @Provides
    fun providesAddToCartUseCase(repository: RemoteRepository): AddToCartUseCase {
        return AddToCartUseCase(repository = repository)
    }

    @Provides
    fun providesGetCartUseCase(repository: RemoteRepository): GetCartUseCase {
        return GetCartUseCase(repository = repository)
    }

    @Provides
    fun providesUpdateCartItemUseCase(repository: RemoteRepository): UpdateCartItemUseCase {
        return UpdateCartItemUseCase(repository = repository)
    }

    @Provides
    fun providesRemoveCartItemUseCase(repository: RemoteRepository): RemoveCartItemUseCase {
        return RemoveCartItemUseCase(repository = repository)
    }

    @Provides
    fun providesClearCartUseCase(repository: RemoteRepository): ClearCartUseCase {
        return ClearCartUseCase(repository = repository)
    }
}