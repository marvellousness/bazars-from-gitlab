package tungp.android.bazarbooks.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import tungp.android.bazarbooks.domain.repository.RemoteRepository
import tungp.android.bazarbooks.data.repository.RemoteRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun bindBookRepository(bookRepositoryImpl: RemoteRepositoryImpl): RemoteRepository
}