package com.app.lastfmcase.domain.model.local

data class SavedAlbumDetail(
    val artist: String,
    val image: String,
    //val tracks: List<SavedAlbumTrack>,
    val url: String,
    val albumName: String
)

data class SavedAlbumTrack(
    val name: String,
    val url: String
)