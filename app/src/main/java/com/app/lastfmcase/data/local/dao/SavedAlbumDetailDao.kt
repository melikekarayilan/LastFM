package com.app.lastfmcase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.lastfmcase.data.local.model.AlbumDetailEntity

@Dao
interface SavedAlbumDetailDao {

    @Insert
    suspend fun insertAlbumDetail(vararg albumDetail: AlbumDetailEntity)

    @Query("SELECT * FROM album_detail WHERE albumName = :albumName")
    suspend fun getSavedAlbumDetail(albumName: String): AlbumDetailEntity

    @Query("SELECT EXISTS (SELECT 1 FROM album_detail WHERE albumName = :name)")
    suspend fun exists(name: String): Boolean

    @Query("DELETE FROM album_detail WHERE albumName = :albumName")
    suspend fun deleteAlbumDetail(albumName: String)

}