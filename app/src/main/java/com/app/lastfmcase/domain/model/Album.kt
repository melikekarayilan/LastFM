package com.app.lastfmcase.domain.model

import com.google.gson.annotations.SerializedName

data class AlbumItem(
    val name: String,
    val mbid: String,
    val url: String,
    @SerializedName("artist")
    val artistOfAlbum: ArtistOfAlbum,
    val image: List<Image>
)

data class ArtistOfAlbum(
    val name: String,
    val mbid: String,
    val url: String
)

data class Album(
    @SerializedName("album")
    val album: List<AlbumItem>
)

data class TopAlbums(
    @SerializedName("topalbums")
    val albums: Album
)

