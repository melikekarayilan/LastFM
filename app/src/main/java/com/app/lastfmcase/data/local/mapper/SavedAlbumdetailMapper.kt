package com.app.lastfmcase.data.local.mapper

import com.app.lastfmcase.data.local.model.AlbumDetailEntity
import com.app.lastfmcase.data.local.model.TrackEntity
import com.app.lastfmcase.domain.model.local.SavedAlbumDetail
import com.app.lastfmcase.domain.model.local.SavedAlbumTrack

class SavedAlbumDetailMapper {
    fun toAlbumDetailModel(model: AlbumDetailEntity): SavedAlbumDetail {
        return SavedAlbumDetail(
            albumName = model.albumName,
            artist = model.artistName,
            image = model.image,
            url = model.url,
            //tracks = toAlbumDetailTrackModel(model.trackList)
        )
    }

    fun toAlbumDetailEntity(model: SavedAlbumDetail): AlbumDetailEntity {
        return AlbumDetailEntity(
            albumName = model.albumName,
            artistName = model.artist,
            image = model.image,
            url = model.url,
            // trackList = toAlbumDetailTrackEntity(model.tracks)
        )
    }

    private fun toAlbumDetailTrackEntity(track: List<SavedAlbumTrack>?): List<TrackEntity> {
        var trackEntityList = mutableListOf<TrackEntity>()
        track?.let {
            for (i in track.indices) {
                trackEntityList.add(
                    i,
                    TrackEntity(trackName = track[i].name, trackUrl = track[i].url)
                )
            }
        }
        return trackEntityList
    }

    private fun toAlbumDetailTrackModel(track: List<TrackEntity>?): List<SavedAlbumTrack> {
        var trackModelList = mutableListOf<SavedAlbumTrack>()
        track?.let {
            for (i in track.indices) {
                trackModelList.add(
                    i,
                    SavedAlbumTrack(name = track[i].trackName, url = track[i].trackUrl)
                )
            }
        }
        return trackModelList
    }
}