package tungp.android.bazarbooks.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tungp.android.bazarbooks.domain.repository.AuthorRepository
import tungp.android.bazarbooks.domain.repository.BookRepository
import tungp.android.bazarbooks.domain.repository.CartRepository
import tungp.android.bazarbooks.domain.repository.VendorRepository
import tungp.android.bazarbooks.domain.repository.CategoryRepository

import tungp.android.bazarbooks.domain.usecase.AddToCartUseCase
import tungp.android.bazarbooks.domain.usecase.GetBookDetailUseCase
import tungp.android.bazarbooks.domain.usecase.GetCategoriesUseCase
import tungp.android.bazarbooks.domain.usecase.GetHomeFeedsUseCase
import tungp.android.bazarbooks.domain.usecase.GetVendorsUseCase
import tungp.android.bazarbooks.domain.usecase.GetCartUseCase
import tungp.android.bazarbooks.domain.usecase.UpdateCartItemUseCase
import tungp.android.bazarbooks.domain.usecase.RemoveCartItemUseCase
import tungp.android.bazarbooks.domain.usecase.GetAuthorsUseCase
import tungp.android.bazarbooks.domain.usecase.ClearCartUseCase
import tungp.android.bazarbooks.domain.usecase.ConfirmOrderUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    fun providesGetHomeFeedsUseCase(repository: BookRepository): GetHomeFeedsUseCase {
        return GetHomeFeedsUseCase(repository = repository)
    }
    
    @Provides
    fun providesGetCategoriesUseCase(repository: CategoryRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository = repository)
    }
    
    @Provides
    fun providesGetVendorsUseCase(repository: VendorRepository): GetVendorsUseCase {
        return GetVendorsUseCase(repository = repository)
    }

    @Provides
    fun providesGetAuthorsUseCase(repository: AuthorRepository): GetAuthorsUseCase {
        return GetAuthorsUseCase(repository = repository)
    }
    
    @Provides
    fun providesGetBookDetailUseCase(repository: BookRepository): GetBookDetailUseCase {
        return GetBookDetailUseCase(repository = repository)
    }
    
    @Provides
    fun providesAddToCartUseCase(repository: CartRepository): AddToCartUseCase {
        return AddToCartUseCase(repository = repository)
    }

    @Provides
    fun providesGetCartUseCase(repository: CartRepository): GetCartUseCase {
        return GetCartUseCase(repository = repository)
    }

    @Provides
    fun providesUpdateCartItemUseCase(repository: CartRepository): UpdateCartItemUseCase {
        return UpdateCartItemUseCase(repository = repository)
    }

    @Provides
    fun providesRemoveCartItemUseCase(repository: CartRepository): RemoveCartItemUseCase {
        return RemoveCartItemUseCase(repository = repository)
    }

    @Provides
    fun providesClearCartUseCase(repository: CartRepository): ClearCartUseCase {
        return ClearCartUseCase(repository = repository)
    }

    @Provides
    fun providesConfirmOrderUseCase(repository: CartRepository): ConfirmOrderUseCase {
        return ConfirmOrderUseCase(repository = repository)
    }
}