package com.app.lastfmcase.domain.use_case.saved_album

import com.app.lastfmcase.domain.repository.saved_album.SavedAlbumRepository
import javax.inject.Inject

class DeleteSavedAlbumDetailUseCase @Inject constructor(
    private val repository: SavedAlbumRepository
) {
    suspend fun execute(album: String) {
        return repository.deleteSavedAlbumDetailUseCase(album)
    }
}