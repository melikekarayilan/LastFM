package com.app.lastfmcase.domain.use_case.album

import com.app.lastfmcase.data.repository.AlbumRepositoryImpl
import com.app.lastfmcase.di.contract.IOResult
import com.app.lastfmcase.di.contract.IUseCase
import com.app.lastfmcase.domain.model.TopAlbums
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopAlbumsUseCase @Inject constructor(private val albumRepositoryImpl: AlbumRepositoryImpl) :
    IUseCase<String, TopAlbums> {

    @ExperimentalCoroutinesApi
    override suspend fun execute(input: String): Flow<IOResult<TopAlbums>> {
        return albumRepositoryImpl.getTopAlbum(input)
    }
}