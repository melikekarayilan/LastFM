package com.app.lastfmcase.di.repository

import com.app.lastfmcase.data.local.dao.SavedAlbumDao
import com.app.lastfmcase.data.local.dao.SavedAlbumDetailDao
import com.app.lastfmcase.data.local.mapper.SavedAlbumDetailMapper
import com.app.lastfmcase.data.local.mapper.SavedAlbumMapper
import com.app.lastfmcase.data.remote.ApiService
import com.app.lastfmcase.data.repository.AlbumDetailRepositoryImpl
import com.app.lastfmcase.data.repository.AlbumRepositoryImpl
import com.app.lastfmcase.data.repository.SearchRepositoryImpl
import com.app.lastfmcase.data.repository.local_repo.SavedAlbumRepositoryImpl
import com.app.lastfmcase.domain.repository.album.AlbumDetailRepository
import com.app.lastfmcase.domain.repository.album.AlbumRepository
import com.app.lastfmcase.domain.repository.saved_album.SavedAlbumRepository
import com.app.lastfmcase.domain.repository.search.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSearchRepository(
        apiService: ApiService,
        dispatcher: CoroutineDispatcher
    ): SearchRepository {
        return SearchRepositoryImpl(apiService, dispatcher)
    }

    @Singleton
    @Provides
    fun provideAlbumRepository(
        apiService: ApiService,
        dispatcher: CoroutineDispatcher
    ): AlbumRepository {
        return AlbumRepositoryImpl(apiService, dispatcher)
    }

    @Singleton
    @Provides
    fun provideAlbumDetailRepository(
        apiService: ApiService,
        dispatcher: CoroutineDispatcher
    ): AlbumDetailRepository {
        return AlbumDetailRepositoryImpl(apiService, dispatcher)
    }

    @Singleton
    @Provides
    fun provideSavedAlbumRepository(
        savedAlbumDao: SavedAlbumDao,
        savedAlbumDetailDao: SavedAlbumDetailDao,
        dispatcher: CoroutineDispatcher
    ): SavedAlbumRepository {
        return SavedAlbumRepositoryImpl(
            savedAlbumDao,
            savedAlbumDetailDao,
            SavedAlbumMapper(),
            SavedAlbumDetailMapper(),
            dispatcher
        )
    }

}