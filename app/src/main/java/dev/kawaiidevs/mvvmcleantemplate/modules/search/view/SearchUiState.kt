package dev.kawaiidevs.mvvmcleantemplate.modules.search.view

import dev.kawaiidevs.mvvmcleantemplate.modules.search.entities.ItunesItemModelUi


sealed class SearchUiState {
    data class Success(val data: List<ItunesItemModelUi>?) : SearchUiState()
    object Loading : SearchUiState()
    object ShowNoConnectivityError : SearchUiState()
    object Error : SearchUiState()
    object NoDataFound : SearchUiState()
    object Default : SearchUiState()
}