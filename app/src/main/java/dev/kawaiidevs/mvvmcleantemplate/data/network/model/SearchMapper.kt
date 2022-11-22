package dev.kawaiidevs.mvvmcleantemplate.data.network.model

import dev.kawaiidevs.mvvmcleantemplate.domain.model.ItunesEntity
import dev.kawaiidevs.mvvmcleantemplate.domain.model.SearchEntity

fun SearchDto.mapToDomain() = SearchEntity(
    searchCount,
    itunesList.toSearchEntityList()
)

fun List<ItunesDto>.toSearchEntityList(): List<ItunesEntity> {
    return map { itunesEntity ->
        itunesEntity.mapToDomain()
    }
}

fun ItunesDto.mapToDomain() = ItunesEntity(
    wrapperType,
    kind,
    artistId,
    collectionId,
    trackId,
    artistName,
    collectionName,
    trackName,
    collectionCensoredName,
    trackCensoredName,
    artistViewUrl,
    collectionViewUrl,
    trackViewUrl,
    previewUrl,
    artworkUrl30,
    artworkUrl60,
    artworkUrl100,
    collectionPrice,
    trackPrice,
    releaseDate,
    collectionExplicitness,
    trackExplicitness,
    discCount,
    discNumber,
    trackCount,
    trackNumber,
    trackTimeMillis,
    country,
    currency,
    primaryGenreName,
    isStreamable,
    copyright
)