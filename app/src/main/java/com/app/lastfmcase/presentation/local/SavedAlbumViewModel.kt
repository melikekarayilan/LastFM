package com.app.lastfmcase.presentation.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lastfmcase.domain.model.local.SavedAlbum
import com.app.lastfmcase.domain.use_case.saved_album.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedAlbumViewModel @Inject constructor(
    private val getSavedAlbumsUseCase: GetSavedAlbumsUseCase,
    private val deleteSavedAlbumUseCase: DeleteSavedAlbumUseCase,
    private val deleteSavedAlbumDetailUseCase: DeleteSavedAlbumDetailUseCase
) :
    ViewModel() {

    private var _savedAlbums: MutableLiveData<List<SavedAlbum>> = MutableLiveData()
    val savedAlbums: LiveData<List<SavedAlbum>>
        get() = _savedAlbums

    fun getSavedAlbums() {
        viewModelScope.launch {
            getSavedAlbumsUseCase.execute()
                .collectLatest {
                    _savedAlbums.postValue(it)
                }
        }
    }

    fun deleteSavedAlbum(album: SavedAlbum) {
        viewModelScope.launch {
            deleteSavedAlbumUseCase.execute(album.albumName)
            val items = _savedAlbums.value?.toMutableList()
            items?.remove(album)
            _savedAlbums.postValue(items!!)
        }
    }

    fun deleteSavedAlbumDetail(album: String) {
        viewModelScope.launch {
            deleteSavedAlbumDetailUseCase.execute(album)
        }
    }
}