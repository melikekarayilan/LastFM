package com.app.lastfmcase.presentation.album_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.domain.model.AlbumDetailRequest
import com.app.lastfmcase.domain.model.AlbumDetailResponse
import com.app.lastfmcase.domain.model.local.SavedAlbumDetail
import com.app.lastfmcase.domain.use_case.album.GetAlbumDetailUseCase
import com.app.lastfmcase.domain.use_case.saved_album.GetSavedAlbumDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val getAlbumDetailUseCase: GetAlbumDetailUseCase,
    private val getSavedAlbumDetailUseCase: GetSavedAlbumDetailUseCase
) :
    ViewModel() {
    private var _albumDetail = MutableLiveData<AlbumDetailResponse>()
    val albumDetail: LiveData<AlbumDetailResponse>
        get() = _albumDetail

    private var _localAlbumDetail = MutableLiveData<SavedAlbumDetail>()
    val localAlbumDetail: LiveData<SavedAlbumDetail>
        get() = _localAlbumDetail

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getAlbumDetail(album: String, artist: String) {
        viewModelScope.launch {
            getAlbumDetailUseCase.execute(
                AlbumDetailRequest(
                    artistName = artist,
                    albumName = album
                )
            ).collectLatest { result ->
                when (result) {
                    is IOResult.OnSuccess -> {
                        _albumDetail.postValue(result.data)
                    }
                    is IOResult.OnError -> {
                        _error.value = result.message
                    }
                }
            }
        }
    }

    fun getSavedAlbumDetail(albumName: String) {
        viewModelScope.launch {
            getSavedAlbumDetailUseCase.execute(albumName)
                .collectLatest {
                    _localAlbumDetail.postValue(it)
                }
        }
    }
}