package com.app.lastfmcase.data.repository

import com.app.lastfmcase.data.remote.ApiResponse
import com.app.lastfmcase.data.remote.ApiService
import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.domain.model.Result
import com.app.lastfmcase.domain.repository.search.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val coroutineDispatcher: CoroutineDispatcher
) :
    SearchRepository, ApiResponse() {
    override suspend fun searchArtist(artist: String): Flow<IOResult<Result>> {
        return flow {
            emit(callApi { apiService.searchArtist(artist, 40) })
        }.flowOn(coroutineDispatcher)
    }
}