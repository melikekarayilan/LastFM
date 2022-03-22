package com.app.lastfmcase.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.lastfmcase.data.local.dao.SavedAlbumDao
import com.app.lastfmcase.data.local.dao.SavedAlbumDetailDao
import com.app.lastfmcase.data.local.model.AlbumDetailEntity
import com.app.lastfmcase.data.local.model.AlbumEntity
import com.app.lastfmcase.data.local.model.TrackEntity

@Database(
    entities = [AlbumEntity::class, AlbumDetailEntity::class, TrackEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AlbumDataBase : RoomDatabase() {

    abstract fun savedAlbumDao(): SavedAlbumDao
    abstract fun savedAlbumDetailDao(): SavedAlbumDetailDao

    companion object {
        @Volatile
        private var instance: AlbumDataBase? = null

        fun getInstance(context: Context): AlbumDataBase {
            return instance ?: synchronized(this) {
                instance ?: build(context).also { instance = it }
            }
        }

        private fun build(context: Context): AlbumDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                AlbumDataBase::class.java,
                "albumDb"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}