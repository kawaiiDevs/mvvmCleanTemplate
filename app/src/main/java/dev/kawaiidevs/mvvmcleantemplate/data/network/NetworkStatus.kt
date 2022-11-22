package dev.kawaiidevs.mvvmcleantemplate.data.network

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}