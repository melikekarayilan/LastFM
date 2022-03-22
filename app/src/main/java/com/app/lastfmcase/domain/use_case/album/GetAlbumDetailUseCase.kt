package com.app.lastfmcase.domain.use_case.album

import com.app.lastfmcase.data.repository.AlbumDetailRepositoryImpl
import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.di.contract.IUseCase
import com.app.lastfmcase.domain.model.AlbumDetailRequest
import com.app.lastfmcase.domain.model.AlbumDetailResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumDetailUseCase @Inject constructor(private val albumDetailRepositoryImpl: AlbumDetailRepositoryImpl) :
    IUseCase<AlbumDetailRequest, AlbumDetailResponse> {

    @ExperimentalCoroutinesApi
    override suspend fun execute(requestInfo: AlbumDetailRequest): Flow<IOResult<AlbumDetailResponse>> {
        return albumDetailRepositoryImpl.getAlbumDetail(
            requestInfo.artistName,
            requestInfo.albumName
        )
    }
}