package com.app.lastfmcase.domain.model

import com.google.gson.annotations.SerializedName

data class ArtistItem(
    val name: String,
    val mbid: String,
    val url: String,
    val image: List<Image>
)

data class Artist(
    @SerializedName("artist")
    val artistItemList: List<ArtistItem>
)

data class ArtistMatches(
    @SerializedName("artistmatches")
    val artist: Artist
)

data class Result(
    @SerializedName("results")
    val artistMatches: ArtistMatches
)

