package dev.kawaiidevs.mvvmcleantemplate.modules.search.entities

import dev.kawaiidevs.mvvmcleantemplate.domain.constants.DATE_PATTERN
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_LONG
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_STRING
import dev.kawaiidevs.mvvmcleantemplate.domain.model.ItunesEntity
import dev.kawaiidevs.mvvmcleantemplate.utils.formatDate
import java.io.Serializable

data class ItunesItemModelUi(
    val artistId: Long? = EMPTY_LONG,
    val artworkUrl60: String? = EMPTY_STRING,
    val collectionName: String? = EMPTY_STRING,
    val releaseDate: String? = EMPTY_STRING,
    val primaryGenreName: String? = EMPTY_STRING,
    val collectionPrice: String? = EMPTY_STRING,
    val currency: String? = EMPTY_STRING,
    val copyright: String? = EMPTY_STRING
) : Serializable {
    companion object {
        fun mapFromDomain(itunesItem: ItunesEntity) = ItunesItemModelUi(
            itunesItem.artistId,
            itunesItem.artworkUrl100,
            itunesItem.collectionName,
            itunesItem.releaseDate?.formatDate(DATE_PATTERN),
            itunesItem.primaryGenreName,
            itunesItem.collectionPrice.toString(),
            itunesItem.currency,
            itunesItem.copyright
        )
    }
}
