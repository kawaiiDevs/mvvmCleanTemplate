package dev.kawaiidevs.mvvmcleantemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kawaiidevs.mvvmcleantemplate.data.network.Api
import dev.kawaiidevs.mvvmcleantemplate.data.repository.ItunesRepositoryImp
import dev.kawaiidevs.mvvmcleantemplate.domain.repository.ItunesRepository
import dev.kawaiidevs.mvvmcleantemplate.domain.usecases.ItunesUseCase
import dev.kawaiidevs.mvvmcleantemplate.domain.usecases.ItunesUseCaseImpl
import javax.inject.Singleton

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRemoteRepository(
        api: Api
    ): ItunesRepository = ItunesRepositoryImp(api)

    @Singleton
    @Provides
    fun provideItunesUseCase(
        itunesRepository: ItunesRepository
    ): ItunesUseCase = ItunesUseCaseImpl(itunesRepository)

}