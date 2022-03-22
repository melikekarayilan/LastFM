package com.app.lastfmcase.domain.model

import com.google.gson.annotations.SerializedName

data class AlbumDetail(
    val artist: String,
    val mbid: String,
    val image: List<Image>,
    @SerializedName("tracks")
    val tracks: Track,
    val url: String,
    val name: String
)

data class Track(
    @SerializedName("track")
    val track: List<TrackItem>,
)

data class TrackItem(
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String
)

data class AlbumDetailResponse(
    @SerializedName("album")
    val album: AlbumDetail
)