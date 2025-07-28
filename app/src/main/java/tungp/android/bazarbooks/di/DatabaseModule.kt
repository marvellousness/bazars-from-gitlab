package tungp.android.bazarbooks.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tungp.android.bazarbooks.data.local.database.BazarBooksDatabase
import tungp.android.bazarbooks.data.local.database.DatabaseProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBookDatabase(@ApplicationContext context: Context) =
        DatabaseProvider.getDatabase(context)

    @Provides
    fun provideBookDao(bazarBooksDatabase: BazarBooksDatabase) = bazarBooksDatabase.bookDao()

    @Provides
    fun provideAuthorDao(bazarBooksDatabase: BazarBooksDatabase) = bazarBooksDatabase.authorDao()
}