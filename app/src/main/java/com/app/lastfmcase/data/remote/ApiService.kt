package com.app.lastfmcase.data.remote

import com.app.lastfmcase.domain.model.AlbumDetailResponse
import com.app.lastfmcase.domain.model.Result
import com.app.lastfmcase.domain.model.TopAlbums
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/2.0/?method=artist.search")
    suspend fun searchArtist(
        @Query("artist") artist: String,
        @Query("limit") limit: Int?
    ): Response<Result>

    @GET("/2.0/?method=artist.getTopAlbums")
    suspend fun getTopAlbums(
        @Query("artist") artist: String,
        @Query("limit") limit: Int?
    ): Response<TopAlbums>

    @GET("/2.0/?method=album.getInfo")
    suspend fun getAlbumDetail(
        @Query("artist") artist: String,
        @Query("album") album: String
    ): Response<AlbumDetailResponse>

}