package com.app.lastfmcase.data.repository

import com.app.lastfmcase.data.remote.ApiResponse
import com.app.lastfmcase.data.remote.ApiService
import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.domain.model.AlbumDetailResponse
import com.app.lastfmcase.domain.repository.album.AlbumDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlbumDetailRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val coroutineDispatcher: CoroutineDispatcher
) : AlbumDetailRepository, ApiResponse() {
    override suspend fun getAlbumDetail(
        artist: String,
        album: String
    ): Flow<IOResult<AlbumDetailResponse>> {
        return flow {
            emit(callApi { apiService.getAlbumDetail(artist, album) })
        }.flowOn(coroutineDispatcher)
    }
}