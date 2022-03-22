package com.app.lastfmcase.di.local

import com.app.lastfmcase.data.local.AlbumDataBase
import android.content.Context
import com.app.lastfmcase.data.local.dao.SavedAlbumDao
import com.app.lastfmcase.data.local.dao.SavedAlbumDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AlbumDataBase {
        return AlbumDataBase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideSavedAlbumDao(db: AlbumDataBase): SavedAlbumDao {
        return db.savedAlbumDao()
    }

    @Singleton
    @Provides
    fun provideSavedAlbumDetailDao(db: AlbumDataBase): SavedAlbumDetailDao {
        return db.savedAlbumDetailDao()
    }
}