package com.app.lastfmcase.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.domain.model.Result
import com.app.lastfmcase.domain.use_case.search.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchArtistUseCase: SearchArtistUseCase
) :
    ViewModel() {

    private var _artistList = MutableLiveData<Result>()
    val artistList: LiveData<Result>
        get() = _artistList

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getArtistList(artist: String) {
        viewModelScope.launch {
            searchArtistUseCase.execute(artist).collectLatest { result ->
                when (result) {
                    is IOResult.OnSuccess -> {
                        _artistList.postValue(result.data)
                    }
                    is IOResult.OnError -> {
                        _error.value = result.message
                    }
                }
            }
        }
    }
}