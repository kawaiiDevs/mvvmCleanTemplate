package dev.kawaiidevs.mvvmcleantemplate.modules.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kawaiidevs.mvvmcleantemplate.domain.ResultItunes
import dev.kawaiidevs.mvvmcleantemplate.domain.constants.EMPTY_INT
import dev.kawaiidevs.mvvmcleantemplate.domain.model.SearchEntity
import dev.kawaiidevs.mvvmcleantemplate.domain.usecases.ItunesUseCase
import dev.kawaiidevs.mvvmcleantemplate.modules.search.entities.ItunesItemModelUi
import dev.kawaiidevs.mvvmcleantemplate.modules.search.view.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val itunerUseCase: ItunesUseCase
) : ViewModel() {

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Default)
    val searchUiState: StateFlow<SearchUiState> get() = _searchUiState

    fun getItunesList(query: String) {

        viewModelScope.launch {
            itunerUseCase.getItunesList(query).collect() { search ->
                _searchUiState.value = SearchUiState.Loading
                handleResponseSearch(search)
            }
        }
    }

    private fun handleResponseSearch(result: ResultItunes<SearchEntity>) {
        when (result) {
            ResultItunes.Loading -> {
                _searchUiState.value = SearchUiState.Loading
            }
            is ResultItunes.Success -> {
                if (result.data.searchCount == EMPTY_INT) {
                    _searchUiState.value = SearchUiState.NoDataFound
                } else {
                    val itunesList =  result.data.itunesList?.let{ itunesList ->
                        itunesList.map { itunesEntity ->
                            ItunesItemModelUi.mapFromDomain(itunesEntity)
                        }
                    }
                    _searchUiState.value = SearchUiState.Success(itunesList)
                }
            }
            is ResultItunes.Error -> {
                _searchUiState.value = SearchUiState.Error
            }
            is ResultItunes.InternetConnectionError -> {
                _searchUiState.value =
                    SearchUiState.ShowNoConnectivityError
            }
        }
    }
}