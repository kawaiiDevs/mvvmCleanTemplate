package dev.kawaiidevs.mvvmcleantemplate.domain.usecases

import dev.kawaiidevs.mvvmcleantemplate.domain.ResultItunes
import dev.kawaiidevs.mvvmcleantemplate.domain.model.SearchEntity
import kotlinx.coroutines.flow.Flow

interface ItunesUseCase {
        suspend fun getItunesList(artistName: String): Flow<ResultItunes<SearchEntity>>
}