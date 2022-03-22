package com.app.lastfmcase.data.repository

import com.app.lastfmcase.data.remote.ApiResponse
import com.app.lastfmcase.data.remote.ApiService
import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.domain.model.TopAlbums
import com.app.lastfmcase.domain.repository.album.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val coroutineDispatcher: CoroutineDispatcher
) : AlbumRepository, ApiResponse() {
    override suspend fun getTopAlbum(artist: String): Flow<IOResult<TopAlbums>> {
        return flow {
            emit(callApi { apiService.getTopAlbums(artist, 40) })
        }.flowOn(coroutineDispatcher)
    }

}