package tungp.android.bazarbooks.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import tungp.android.bazarbooks.data.repository.AuthorRepositoryImpl
import tungp.android.bazarbooks.data.repository.CategoryRepositoryImpl

import tungp.android.bazarbooks.domain.repository.CategoryRepository
import tungp.android.bazarbooks.data.repository.VendorRepositoryImpl
import tungp.android.bazarbooks.domain.repository.AuthorRepository
import tungp.android.bazarbooks.domain.repository.VendorRepository
import tungp.android.bazarbooks.domain.repository.BookRepository
import tungp.android.bazarbooks.data.repository.BookRepositoryImpl
import tungp.android.bazarbooks.data.repository.CartRepositoryImpl
import tungp.android.bazarbooks.domain.repository.CartRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    

    @Binds
    @ViewModelScoped
    abstract fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @ViewModelScoped
    abstract fun bindVendorRepository(vendorRepositoryImpl: VendorRepositoryImpl): VendorRepository

    @Binds
    @ViewModelScoped
    abstract fun bindAuthorRepository(authorRepositoryImpl: AuthorRepositoryImpl): AuthorRepository

    @Binds
    @ViewModelScoped
    abstract fun bindBookRepositoryImpl(bookRepositoryImpl: BookRepositoryImpl): BookRepository

    @Binds
    @ViewModelScoped
    abstract fun bindCartRepository(cartRepositoryImpl: CartRepositoryImpl): CartRepository
}