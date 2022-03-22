package com.app.lastfmcase.domain.repository.album

import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.domain.model.TopAlbums
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getTopAlbum(artist: String): Flow<IOResult<TopAlbums>>
}