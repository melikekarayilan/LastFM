package com.app.lastfmcase.domain.use_case.saved_album

import com.app.lastfmcase.domain.model.local.SavedAlbum
import com.app.lastfmcase.domain.repository.saved_album.SavedAlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedAlbumsUseCase @Inject constructor(
    private val repository: SavedAlbumRepository
) {
    suspend fun execute(): Flow<List<SavedAlbum>> {
        return repository.getSavedAlbum()
    }
}