package com.app.lastfmcase.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_detail")
data class AlbumDetailEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "albumName") val albumName: String,
    @ColumnInfo(name = "artistName") val artistName: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "url") val url: String,
    //@Embedded val trackList: List<TrackEntity>
)

@Entity(tableName = "track_entity")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val trackName: String,
    @ColumnInfo(name = "url") val trackUrl: String
)