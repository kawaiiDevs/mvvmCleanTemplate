package dev.kawaiidevs.mvvmcleantemplate.domain

import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_INT

sealed class ResultItunes<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultItunes<T>()
    data class Error(val exception: String?, val errorCode: Int = EMPTY_INT) : ResultItunes<Nothing>()
    object InternetConnectionError : ResultItunes<Nothing>()
    object Loading : ResultItunes<Nothing>()
}
