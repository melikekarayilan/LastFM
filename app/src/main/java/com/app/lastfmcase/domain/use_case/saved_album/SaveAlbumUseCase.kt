package com.app.lastfmcase.domain.use_case.saved_album

import com.app.lastfmcase.domain.model.local.SavedAlbum
import com.app.lastfmcase.domain.repository.saved_album.SavedAlbumRepository
import javax.inject.Inject

class SaveAlbumUseCase @Inject constructor(
    private val repository: SavedAlbumRepository
) {
    suspend fun execute(params: SavedAlbum) {
        return repository.saveAlbum(params)
    }
}