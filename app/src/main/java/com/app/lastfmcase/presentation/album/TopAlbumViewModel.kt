package com.app.lastfmcase.presentation.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.domain.model.*
import com.app.lastfmcase.domain.model.local.SavedAlbum
import com.app.lastfmcase.domain.model.local.SavedAlbumDetail
import com.app.lastfmcase.domain.use_case.album.GetTopAlbumsUseCase
import com.app.lastfmcase.domain.use_case.saved_album.SaveAlbumDetailUseCase
import com.app.lastfmcase.domain.use_case.saved_album.SaveAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopAlbumViewModel @Inject constructor(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val saveAlbumUseCase: SaveAlbumUseCase,
    private val saveAlbumDetailUseCase: SaveAlbumDetailUseCase
) :
    ViewModel() {
    private var _topAlbums: MutableLiveData<TopAlbums> = MutableLiveData()
    val topAlbums: LiveData<TopAlbums>
        get() = _topAlbums

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getTopAlbum(artist: String) {
        viewModelScope.launch {
            getTopAlbumsUseCase.execute(artist).collectLatest { result ->
                when (result) {
                    is IOResult.OnSuccess -> {
                        _topAlbums.postValue(result.data)
                    }
                    is IOResult.OnError -> {
                        _error.value = result.message
                    }
                }
            }
        }
    }

    fun saveAlbumDetail(detail: AlbumDetailResponse) {
        viewModelScope.launch {
            saveAlbumDetailUseCase.execute(
                SavedAlbumDetail(
                    albumName = detail.album.name,
                    artist = detail.album.artist,
                    image = detail.album.image[2].text,
                    url = detail.album.url,
                    //tracks = toSavedAlbumTrack(detail.album.tracks.track)
                )
            )
        }
    }

    fun saveAlbum(album: AlbumItem) {
        viewModelScope.launch {
            saveAlbumUseCase.execute(
                SavedAlbum(
                    albumName = album.name,
                    url = album.url,
                    artistName = album.artistOfAlbum.name,
                    img = album.image[2].text
                )
            )
        }
    }

    /* private fun toSavedAlbumTrack(trackList: List<TrackItem>): List<SavedAlbumTrack> {
         var trackModelList = mutableListOf<SavedAlbumTrack>()
         trackList?.let {
             for (i in trackList.indices) {
                 trackModelList.add(
                     i,
                     SavedAlbumTrack(name = trackList[i].name, url = trackList[i].url)
                 )
             }
         }
         return trackModelList
     }*/
}