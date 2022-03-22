package com.app.lastfmcase.domain.use_case.saved_album

import com.app.lastfmcase.domain.model.local.SavedAlbumDetail
import com.app.lastfmcase.domain.repository.saved_album.SavedAlbumRepository
import javax.inject.Inject

class SaveAlbumDetailUseCase @Inject constructor(
    private val repository: SavedAlbumRepository
) {
    suspend fun execute(params: SavedAlbumDetail) {
        return repository.saveAlbumDetail(params)
    }
}