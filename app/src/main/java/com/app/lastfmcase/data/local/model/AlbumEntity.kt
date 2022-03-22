package com.app.lastfmcase.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "artistName") val artistName: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "url") val url: String
)