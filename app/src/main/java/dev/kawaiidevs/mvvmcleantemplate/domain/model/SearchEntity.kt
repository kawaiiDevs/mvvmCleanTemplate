package dev.kawaiidevs.mvvmcleantemplate.domain.model

import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_INT

data class SearchEntity(
    val searchCount: Int? = EMPTY_INT,
    val itunesList: List<ItunesEntity>? = emptyList()
)