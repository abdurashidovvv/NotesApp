package uz.abdurashidov.notesapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.abdurashidov.notesapp.data.repository.NotesRepositoryImpl
import uz.abdurashidov.notesapp.domain.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: NotesRepositoryImpl): Repository
}