package com.app.lastfmcase.data.repository.local_repo

import com.app.lastfmcase.data.local.dao.SavedAlbumDao
import com.app.lastfmcase.data.local.dao.SavedAlbumDetailDao
import com.app.lastfmcase.data.local.mapper.SavedAlbumDetailMapper
import com.app.lastfmcase.data.local.mapper.SavedAlbumMapper
import com.app.lastfmcase.domain.model.local.SavedAlbum
import com.app.lastfmcase.domain.model.local.SavedAlbumDetail
import com.app.lastfmcase.domain.repository.saved_album.SavedAlbumRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedAlbumRepositoryImpl @Inject constructor(
    private val savedAlbumDao: SavedAlbumDao,
    private val savedAlbumDetailDao: SavedAlbumDetailDao,
    private val savedAlbumMapper: SavedAlbumMapper,
    private val savedAlbumDetailMapper: SavedAlbumDetailMapper,
    private val coroutineDispatcher: CoroutineDispatcher

) : SavedAlbumRepository {

    override suspend fun getSavedAlbum(): Flow<List<SavedAlbum>> {
        return flow {
            emit(savedAlbumDao.getAllSavedAlbums().map {
                savedAlbumMapper.toAlbumModel(it)
            })
        }.flowOn(coroutineDispatcher)
    }

    override suspend fun saveAlbum(savedAlbum: SavedAlbum) {
        CoroutineScope(coroutineDispatcher).launch {
            if (!savedAlbumDao.exists(savedAlbum.albumName)) {
                savedAlbumDao.insertAlbum(savedAlbumMapper.toAlbumEntity(savedAlbum))
            }
        }
    }

    override suspend fun deleteSavedAlbum(album: String) {
        CoroutineScope(coroutineDispatcher).launch {
            savedAlbumDao.deleteAlbum(album)
        }
    }

    override suspend fun saveAlbumDetail(album: SavedAlbumDetail) {
        CoroutineScope(coroutineDispatcher).launch {
            if (!savedAlbumDetailDao.exists(album.albumName)) {
                savedAlbumDetailDao.insertAlbumDetail(
                    savedAlbumDetailMapper.toAlbumDetailEntity(
                        album
                    )
                )
            }
        }
    }

    override suspend fun getSavedAlbumDetail(album: String): Flow<SavedAlbumDetail> {
        return flow {
            if (savedAlbumDetailDao.exists(album)) {
                emit(
                    savedAlbumDetailMapper.toAlbumDetailModel(
                        savedAlbumDetailDao.getSavedAlbumDetail(
                            album
                        )
                    )
                )
            }
        }.flowOn(coroutineDispatcher)
    }

    override suspend fun deleteSavedAlbumDetailUseCase(albumName: String) {
        CoroutineScope(coroutineDispatcher).launch {
            savedAlbumDetailDao.deleteAlbumDetail(albumName)
        }
    }
}