package dev.kawaiidevs.mvvmcleantemplate.domain.usecases

import dagger.hilt.android.scopes.ActivityScoped
import dev.kawaiidevs.mvvmcleantemplate.domain.ResultItunes
import dev.kawaiidevs.mvvmcleantemplate.domain.model.SearchEntity
import dev.kawaiidevs.mvvmcleantemplate.domain.repository.ItunesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityScoped
class ItunesUseCaseImpl @Inject constructor(
    private val repository: ItunesRepository
): ItunesUseCase {

    override suspend fun getItunesList(artistName: String): Flow<ResultItunes<SearchEntity>> {
        return repository.getItunesList(artistName)
    }
}