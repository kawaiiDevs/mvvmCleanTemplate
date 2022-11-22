package dev.kawaiidevs.mvvmcleantemplate.domain.repository

import dev.kawaiidevs.mvvmcleantemplate.domain.ResultItunes
import dev.kawaiidevs.mvvmcleantemplate.domain.model.SearchEntity
import kotlinx.coroutines.flow.Flow

interface ItunesRepository {
    suspend fun getItunesList(artistName: String): Flow<ResultItunes<SearchEntity>>
}