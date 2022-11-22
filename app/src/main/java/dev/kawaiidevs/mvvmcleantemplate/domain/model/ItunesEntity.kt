package dev.kawaiidevs.mvvmcleantemplate.domain.model

import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_DOUBLE
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_INT
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_LONG
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_STRING

data class ItunesEntity(
    val wrapperType: String? = EMPTY_STRING,
    val kind: String? = EMPTY_STRING,
    val artistId: Long? = EMPTY_LONG,
    val collectionId: Long? = EMPTY_LONG,
    val trackId: Long? = EMPTY_LONG,
    val artistName: String? = EMPTY_STRING,
    val collectionName: String? = EMPTY_STRING,
    val trackName: String? = EMPTY_STRING,
    val collectionCensoredName: String? = EMPTY_STRING,
    val trackCensoredName: String? = EMPTY_STRING,
    val artistViewUrl: String? = EMPTY_STRING,
    val collectionViewUrl: String? = EMPTY_STRING,
    val trackViewUrl: String? = EMPTY_STRING,
    val previewUrl: String? = EMPTY_STRING,
    val artworkUrl30: String? = EMPTY_STRING,
    val artworkUrl60: String? = EMPTY_STRING,
    val artworkUrl100: String? = EMPTY_STRING,
    val collectionPrice: Double? = EMPTY_DOUBLE,
    val trackPrice: Double? = EMPTY_DOUBLE,
    val releaseDate: String? = EMPTY_STRING,
    val collectionExplicitness: String? = EMPTY_STRING,
    val trackExplicitness: String? = EMPTY_STRING,
    val discCount: Int? = EMPTY_INT,
    val discNumber: Int? = EMPTY_INT,
    val trackCount: Int? = EMPTY_INT,
    val trackNumber: Int? = EMPTY_INT,
    val trackTimeMillis: Long? = EMPTY_LONG,
    val country: String? = EMPTY_STRING,
    val currency: String? = EMPTY_STRING,
    val primaryGenreName: String? = EMPTY_STRING,
    val isStreamable: Boolean? = false,
    val copyright: String? = EMPTY_STRING,
)