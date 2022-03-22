package com.app.lastfmcase.domain.repository.search

import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchArtist(artist: String): Flow<IOResult<Result>>
}