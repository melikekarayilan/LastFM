package com.app.lastfmcase.domain.repository.album

import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.domain.model.AlbumDetailResponse
import kotlinx.coroutines.flow.Flow

interface AlbumDetailRepository {
    suspend fun getAlbumDetail(artist: String, album: String): Flow<IOResult<AlbumDetailResponse>>
}