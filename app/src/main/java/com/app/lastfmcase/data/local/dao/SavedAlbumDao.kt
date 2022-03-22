package com.app.lastfmcase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.lastfmcase.data.local.model.AlbumEntity

@Dao
interface SavedAlbumDao {
    @Query("SELECT * FROM albums")
    suspend fun getAllSavedAlbums(): List<AlbumEntity>

    @Insert
    suspend fun insertAlbum(vararg album: AlbumEntity)

    @Query("SELECT EXISTS (SELECT 1 FROM albums WHERE name = :name)")
    suspend fun exists(name: String): Boolean

    @Query("DELETE FROM albums WHERE name = :name")
    suspend fun deleteAlbum(name: String)
}