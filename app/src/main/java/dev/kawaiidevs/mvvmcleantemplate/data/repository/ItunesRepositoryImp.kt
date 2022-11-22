package dev.kawaiidevs.mvvmcleantemplate.data.repository

import dagger.hilt.android.scopes.ActivityScoped
import dev.kawaiidevs.mvvmcleantemplate.data.network.Api
import dev.kawaiidevs.mvvmcleantemplate.data.network.model.mapToDomain
import dev.kawaiidevs.mvvmcleantemplate.data.retrofit.executeRetrofitRequest
import dev.kawaiidevs.mvvmcleantemplate.data.retrofit.handleResultRetrofit
import dev.kawaiidevs.mvvmcleantemplate.domain.ResultItunes
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.ITUNES_DEFAULT_LIMIT
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.ITUNES_ENTITY_ALBUM
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.ITUNES_ENTITY_ATTRIBUTE
import dev.kawaiidevs.mvvmcleantemplate.domain.model.SearchEntity
import dev.kawaiidevs.mvvmcleantemplate.domain.repository.ItunesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityScoped
class ItunesRepositoryImp @Inject constructor(
    private val api: Api
) : ItunesRepository {
    override suspend fun getItunesList(artistName: String): Flow<ResultItunes<SearchEntity>> =
        flow {
            emit(getItunesListHandleResultRetrofit(artistName))
        }

    private suspend fun getItunesListHandleResultRetrofit(artistName: String): ResultItunes<SearchEntity> {
        val result = executeRetrofitRequest {
            api.getItunesList(
                artistName,
                ITUNES_ENTITY_ALBUM,
                ITUNES_ENTITY_ATTRIBUTE,
                ITUNES_DEFAULT_LIMIT
            )
        }
        return handleResultRetrofit(result) { it.mapToDomain() }
    }

}


