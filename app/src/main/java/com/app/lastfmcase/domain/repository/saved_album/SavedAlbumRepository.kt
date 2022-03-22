package com.app.lastfmcase.domain.repository.saved_album

import com.app.lastfmcase.domain.model.local.SavedAlbum
import com.app.lastfmcase.domain.model.local.SavedAlbumDetail
import kotlinx.coroutines.flow.Flow

interface SavedAlbumRepository {
    suspend fun getSavedAlbum(): Flow<List<SavedAlbum>>
    suspend fun saveAlbum(savedAlbum: SavedAlbum)
    suspend fun deleteSavedAlbum(albumName: String)
    suspend fun saveAlbumDetail(album: SavedAlbumDetail)
    suspend fun getSavedAlbumDetail(albumName: String): Flow<SavedAlbumDetail>
    suspend fun deleteSavedAlbumDetailUseCase(albumName: String)
}