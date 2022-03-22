package com.app.lastfmcase.data.local.mapper

import com.app.lastfmcase.data.local.model.AlbumEntity
import com.app.lastfmcase.domain.model.local.SavedAlbum

class SavedAlbumMapper {
    fun toAlbumModel(model: AlbumEntity): SavedAlbum {
        return SavedAlbum(
            albumName = model.name,
            artistName = model.artistName,
            img = model.image,
            url = model.url
        )
    }

    fun toAlbumEntity(model: SavedAlbum): AlbumEntity {
        return AlbumEntity(
            name = model.albumName,
            artistName = model.artistName,
            image = model.img,
            url = model.url
        )
    }
}